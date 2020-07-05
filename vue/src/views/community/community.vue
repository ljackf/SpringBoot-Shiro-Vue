<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form>
        <el-form-item>

          <el-button type="primary" icon="plus" @click="showCreate" v-if="hasPerm('community:add')">添加
          </el-button>

        </el-form-item>
      </el-form>
    </div>

    <el-table :data="list" v-loading.body="listLoading" element-loading-text="拼命加载中" border fit
              highlight-current-row>
      <el-table-column align="center" label="序号" width="80">
        <template slot-scope="scope">
          <span v-text="getIndex(scope.$index)"> </span>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="name" label="社区名称" style="width: 60px;"></el-table-column>
      <el-table-column align="center"  label="地址" style="width: 60px;">
        <template slot-scope="scope">
          <span v-text="getpcd(list[scope.$index].region) + list[scope.$index].address" > </span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="创建时间" prop="createTime" width="170"></el-table-column>
      <el-table-column align="center" label="管理" width="200" v-if="hasPerm('community:update')">
        <template slot-scope="scope">
          <el-button type="primary" icon="edit" @click="showUpdate(scope.$index)">修改</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="listQuery.pageNum"
      :page-size="listQuery.pageRow"
      :total="totalCount"
      :page-sizes="[10, 20, 50, 100]"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form class="small-space" :model="tempCommunity" label-position="left" label-width="200px"
               style='width: 500px; margin-left:50px;'>
        <el-form-item label="社区名称" required>
          <el-input type="text" v-model="tempCommunity.name">
          </el-input>
        </el-form-item>
        <el-form-item label="建筑年代">
          <el-select v-model="tempCommunity.architectureAge" placeholder="请选择">
            <el-option
              v-for="item in ages" :key="item.value" :label="item.name" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="建筑类型">
          <el-select v-model="tempCommunity.architectureType" placeholder="请选择">
            <el-option
              v-for="item in architectureTypes"
              :key="item.value"
              :label="item.name"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="楼栋总数">
          <el-input type="number" v-model="tempCommunity.architectureSum">
          </el-input>
        </el-form-item>
        <el-form-item label="总户数">
          <el-input type="number" v-model="tempCommunity.houseSum">
          </el-input>
        </el-form-item>
        <el-form-item label="物业公司">
          <el-input type="text" v-model="tempCommunity.serviceCompany">
          </el-input>
        </el-form-item>
        <el-form-item label="物业费">
          <el-input type="number" v-model="tempCommunity.serviceFee">
          </el-input>
        </el-form-item>
        <el-form-item label="开发商">
          <el-input type="text" v-model="tempCommunity.developCompany">
          </el-input>
        </el-form-item>
        <el-form-item label="二手房价">
          <el-input type="number" v-model="tempCommunity.secondHandPrice">
          </el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-cascader
            size="large"
            :options="options"
            v-model="tempCommunity.selectedOptions"
            @change="handleChange">
          </el-cascader>
        </el-form-item>
        <el-form-item label="请输入详细街道地址">
          <el-input type="text" v-model="tempCommunity.addressDetail" @keyup.enter.native="getAddressInfo()">
          </el-input>
        </el-form-item>
        <div>
          <div id="allmap" style="width:500px;height:400px;"></div>
          <div>
            经度<el-input  v-model="tempCommunity.longitude" disabled></el-input>
            纬度<el-input  v-model="tempCommunity.latitude" disabled></el-input>
          </div>
        </div>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button v-if="dialogStatus=='create'" type="success" @click="createCommunity">创 建</el-button>
        <el-button type="primary" v-else @click="updateCommunity">修 改</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {provinceAndCityData,regionData,provinceAndCityDataPlus,regionDataPlus,CodeToText,TextToCode} from 'element-china-area-data'
  export default {
    mounted() {
      this.$nextTick(() => {
        this.tencentMap()//调用腾讯地图
      });
    },
    data() {
      return {
        totalCount: 0, //分页组件--数据总条数
        list: [],//表格的数据
        listLoading: false,//数据加载等待动画
        listQuery: {
          pageNum: 1,//页码
          pageRow: 50,//每页条数
          name: ''
        },
        dialogStatus: 'create',
        dialogFormVisible: false,
        textMap: {
          update: '编辑',
          create: '创建社区'
        },
        ages: [
          {"name": '90年代之前', "value": "BEFORE90"},
          {"name": '90年代', "value": "F90T00"},
          {"name": '00年代', "value": "F00T10"},
          {"name": '10年代', "value": "F10T20"},
          {"name": '20年代之后', "value": "AFTER20"},
        ],
        architectureTypes: [
          {"name": '塔楼', "value": "TOWER"},
          {"name": '板楼', "value": "SLAB"}
        ],
        tempCommunity: {
          id: "",
          name: "",
          address: "",
          addressDetail: "",
          architectureAge: "",
          architectureType: "",
          architectureSum: "",
          houseSum: "",
          serviceCompany: "",
          serviceFee: "",
          developCompany: "",
          secondHandPrice: "",
          selectedOptions: [],
          longitude:"",
          latitude:""
        },
        options: regionData

      }
    },
    created() {
      this.getList();
    },
    methods: {
      getList() {
        //查询列表
        if (!this.hasPerm('community:list')) {
          return
        }
        this.listLoading = true;
        this.api({
          url: "/community/listCommunity",
          method: "get",
          params: this.listQuery
        }).then(data => {
          this.listLoading = false;
          this.list = data.list;
          this.totalCount = data.totalCount;
        })
      },
      handleSizeChange(val) {
        //改变每页数量
        this.listQuery.pageRow = val
        this.handleFilter();
      },
      handleCurrentChange(val) {
        //改变页码
        this.listQuery.pageNum = val
        this.getList();
      },
      getIndex($index) {
        //表格序号
        return (this.listQuery.pageNum - 1) * this.listQuery.pageRow + $index + 1
      },
      showCreate() {
        //显示新增对话框
        this.dialogStatus = "create"
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.tencentMap()
        })
      },
      showUpdate($index) {
        //显示修改对话框
        let dbConmunity = this.list[$index];
        this.tempCommunity.name = dbConmunity.name;
        this.tempCommunity.addressDetail = dbConmunity.address;
        this.tempCommunity.architectureAge = dbConmunity.architecture_age;
        this.tempCommunity.architectureType = dbConmunity.architecture_type;
        this.tempCommunity.architectureSum = dbConmunity.architecture_sum;
        this.tempCommunity.serviceCompany = dbConmunity.service_company;
        this.tempCommunity.developCompany = dbConmunity.develop_company;
        this.tempCommunity.serviceFee = dbConmunity.service_fee;
        this.tempCommunity.houseSum = dbConmunity.house_sum;
        this.tempCommunity.secondHandPrice = dbConmunity.second_hand_price;
        this.tempCommunity.longitude = dbConmunity.longitude;
        this.tempCommunity.latitude = dbConmunity.latitude;
        this.tempCommunity.selectedOptions =
          [parseInt(dbConmunity.region/10000)*10000,parseInt(dbConmunity.region/100)*100,dbConmunity.region]
        this.address=this.getCityCode(this.tempCommunity.selectedOptions);
        this.tempCommunity.id = dbConmunity.id;
        this.dialogStatus = "update"
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.tencentMap()//调用腾讯地图
        });
      },
      createCommunity() {
        //保存新房源
        this.api({
          url: "/community/addCommunity",
          method: "post",
          data: this.tempCommunity
        }).then(() => {
          this.getList();
          this.dialogFormVisible = false
        })
      },
      updateCommunity() {
        //修改房源
        this.api({
          url: "/community/updateCommunity",
          method: "post",
          data: this.tempCommunity
        }).then(() => {
          this.getList();
          this.dialogFormVisible = false
        })
      },
      tencentMap() {
        var center = new qq.maps.LatLng(this.longitude, this.latitude);
        var map = new qq.maps.Map(
          document.getElementById('allmap'), {
            center: center,
            zoom: 13,
            draggable: true,
            scrollwheel: true,
            disableDoubleClickZoom: true
          })
        var marker = new qq.maps.Marker({
        //设置Marker的位置坐标
          position: center,
          map: map
        });
        var infoWin = new qq.maps.InfoWindow({
          map: map
        });
        let address1=''
        var geocoder = new qq.maps.Geocoder({
          complete: function(res) {
            console.log(res);
            address1 = res.detail.nearPois[0].name;
          }
        });
        // qq.maps.event.addListener(map, "click", function(event) {
        //   this.longitude = event.latLng.getLat();
        //   this.latitude = event.latLng.getLng();
        //   console.log(event);
        //   let lat = new qq.maps.LatLng(this.longitude, this.latitude);
        //   geocoder.getAddress(lat);
        //   setTimeout(() => {
        //     infoWin.open();
        //     infoWin.setContent(
        //       '<div style="text-align:center;white-space:nowrap;">' +
        //       address1 +
        //       "</div>"
        //     );
        //     infoWin.setPosition(event.latLng);
        //   }, 200);
        // });
      },
      getAddressInfo(){
        this.api({
          method:'get',
          url:'https://bird.ioliu.cn/v1/?url=' +"https://apis.map.qq.com/ws/geocoder/v1/?address="+this.address+this.tempCommunity.addressDetail+"&key=6NSBZ-I7LKU-ZJ7VV-4BA2C-C3VE5-ASBGZ",
          dataType:'JSONP',
        }).then((res)=>{
          if(res.status==0){
            this.tempCommunity.longitude=res.result.location.lat;
            this.tempCommunity.latitude=res.result.location.lng;
            this.tencentMap();//更新地图信息
          }else{
            this.tempCommunity.longitude=0;
            this.tempCommunity.latitude=0;
          }
        }).catch(res => {
          if(res.status==0){
            this.tempCommunity.longitude=res.result.location.lat;
            this.tempCommunity.latitude=res.result.location.lng;
            this.tencentMap();//更新地图信息
          }else{
            this.tempCommunity.longitude=0;
            this.tempCommunity.latitude=0;
          }
        })
        return false;
      },
      handleChange(value) {
        this.address=this.getCityCode(value);
      },
      getCityCode:function(value){
        return CodeToText[value[0]]+CodeToText[value[1]]+CodeToText[value[2]]
      },
      getpcd:function(value){
        return CodeToText[value/10000*10000]+CodeToText[value/100*100]+CodeToText[value]
      }
    }

  }
</script>

<style scoped>

</style>
