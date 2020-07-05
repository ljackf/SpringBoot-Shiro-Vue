<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form>
        <el-form-item>
          <el-button type="primary" icon="plus" @click="showCreate" v-if="hasPerm('article:add')">添加
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
      <el-table-column align="center" prop="name" label="房源" style="width: 60px;"></el-table-column>
      <el-table-column align="center" label="创建时间" width="170">
        <template slot-scope="scope">
          <span>{{scope.row.createTime}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="管理" width="200" v-if="hasPerm('article:update')">
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
      <el-form class="small-space" :model="house" label-position="left" label-width="200px"
               style='width: 500px; margin-left:50px;'>
        <el-form-item label="房源名称">
          <el-input type="text" v-model="house.name">
          </el-input>
        </el-form-item>
        <el-form-item label="选择小区">
          <el-select v-model="house.communityId" placeholder="请选择">
            <el-option
              v-for="item in communitys" :key="item.id" :label="item.name" :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="面积">
          <el-input type="number" v-model="house.area">
          </el-input>
        </el-form-item>
        <el-form-item label="层">
          <el-input type="number" v-model="house.floor">
          </el-input>
        </el-form-item>
        <el-form-item label="室">
          <el-input type="number" v-model="house.room">
          </el-input>
        </el-form-item>
        <el-form-item label="厅">
          <el-input type="number" v-model="house.hall">
          </el-input>
        </el-form-item>
        <el-form-item label="卫">
          <el-input type="number" v-model="house.bathroom">
          </el-input>
        </el-form-item>
        <el-form-item label="描述">
          <el-input type="textarea" v-model="house.des">
          </el-input>
        </el-form-item>
        <el-form-item label="房源头图">
          <el-upload
            class="upload-file"
            action=""
            :drag="true"
            :multiple="false"
            :file-list="house.preImg"
            :http-request="uploadHttp1"
            :before-upload="beforeAvatarUpload1"
            :on-remove="handleRemove1"
            list-type="picture">
            <i class="el-icon-plus avatar-uploader-icon"></i>
            <!--<div class="el-upload__tip" slot="tip">只能上传jpg/jpeg/png文件，且不超过500kb</div>-->
          </el-upload>
        </el-form-item>
        <el-form-item label="房源图片详情">
          <el-upload
            class="upload-file"
            action=""
            :drag="true"
            :multiple="true"
            :file-list="house.houseImg"
            :http-request="uploadHttp"
            :before-upload="beforeAvatarUpload"
            :on-remove="handleRemove"
            list-type="picture">
            <i class="el-icon-plus avatar-uploader-icon"></i>
            <!--<div class="el-upload__tip" slot="tip">只能上传jpg/jpeg/png文件，且不超过500kb</div>-->
          </el-upload>
        </el-form-item>
        <el-form-item label="户型">
          <el-select v-model="house.houseLayout" placeholder="请选择">
            <el-option
              v-for="item in houseLayout"
              :key="item.value"
              :label="item.name"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="付款方式">
          <el-select v-model="house.payMode" placeholder="请选择">
            <el-option
              v-for="item in payMode"
              :key="item.value"
              :label="item.name"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="楼层类型">
          <el-select v-model="house.floorType" placeholder="请选择">
            <el-option
              v-for="item in floorType"
              :key="item.value"
              :label="item.name"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="朝向">
          <el-select v-model="house.orientation" placeholder="请选择">
            <el-option
              v-for="item in orientation"
              :key="item.value"
              :label="item.name"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="装修类型">
          <el-select v-model="house.renovationType" placeholder="请选择">
            <el-option
              v-for="item in renovationType"
              :key="item.value"
              :label="item.name"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="租金（每月）">
          <el-input type="number" v-model="house.rent">
          </el-input>
        </el-form-item>
        <el-form-item label="房屋配置">
          <el-checkbox-group v-model="house.settings">
            <el-checkbox v-for="(value,index) in settings" :label="value.value" :key="value.value"
                         >{{value.name}}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="是否优选">
          <el-radio-group v-model="house.selected">
              <el-radio :label="0">否</el-radio>
              <el-radio :label="1">是</el-radio>
            </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button v-if="dialogStatus=='create'" type="success" @click="createArticle">创 建</el-button>
        <el-button type="primary" v-else @click="updateArticle">修 改</el-button>
      </div>
    </el-dialog>
    <el-dialog :visible.sync="dialogVisible">
      <img width="100%" :src="dialogImageUrl" alt="">
    </el-dialog>
  </div>
</template>
<script>
  var Bucket = 'leileizufang-1302428949';//腾讯云对象储存桶名
  var region = 'ap-beijing';//对象储存你所处的地区编号
  var cos = new COS({
    getAuthorization: function (options, callback) {
      var authorization = COS.getAuthorization({
        SecretId: 'AKIDtet75nGUxBBGK7Gsnb9QcldHFwnPYzC9', //这是密钥ID
        SecretKey: 's2vwjFh6E7SWjgMwTknIXv3uhQt5l9gx', //这是密钥的钥匙（哇，我都不知道怎么说了，总之是在腾讯云对象储存上注册登录后你要申请的东西）
        Method: options.Method,
        Key: options.Key,
        Query: options.Query,
        Headers: options.Headers,
        Expires: 60,
      });
      callback(authorization);
    }
  });
  export default {
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
          create: '创建房源'
        },
        payMode: [
          {"name": '押一付三', "value": "ONEFORTHREE"},
          {"name": '押一付一', "value": "ONEFORONE"},
          {"name": '押二付三', "value": "TWOFORTHREE"},
          {"name": '半年付', "value": "HARFYEAR"},
          {"name": '年付', "value": "ONEYEAR"},
          {"name": '其他', "value": "OTHER"}
        ],
        orientation: [
          {"name": '东', "value": "EAST"},
          {"name": '南', "value": "SOUTH"},
          {"name": '西', "value": "WEST"},
          {"name": '北', "value": "NORTH"},
          {"name": '东南', "value": "SOUTHEAST"},
          {"name": '东北', "value": "NORTHEAST"},
          {"name": '西南', "value": "SOUTHWEST"},
          {"name": '西北', "value": "NORTHWEST"},
          {"name": '南北通透', "value": "NORTHSOUTHPENETRATION"}
        ],
        renovationType: [
          {"name": '豪装', "value": "HAUTECOUTURE"},
          {"name": '精装', "value": "HARDCOVER"},
          {"name": '中装', "value": "MEDIUMDECORATION"},
          {"name": '简装', "value": "PAPERBACK"},
          {"name": '毛坯', "value": "ROUGHCAST"},
        ],
        houseLayout: [
          {"name": '合租', "value": "COTENANCY"},
          {"name": '整租', "value": "WHOLERENT"},
          {"name": '商铺办公', "value": "SHOPOFFICE"}
        ],
        settings: [
          {"name": '电视', "value": "TV"},
          {"name": '冰箱', "value": "ICEBOX"},
          {"name": '洗衣机', "value": "WASHING"},
          {"name": '空调', "value": "COTENANCY"},
          {"name": '热水器', "value": "HEATER"},
          {"name": '床', "value": "BED"},
          {"name": '暖气', "value": "STEAM"},
          {"name": '宽带', "value": "BROADBAND"},
          {"name": '衣柜', "value": "WARDROBE"},
          {"name": '燃气灶', "value": "GASSTOVE"},
          {"name": '沙发', "value": "SOFA"},
          {"name": '桌椅', "value": "TABLESCHAIRS"},
          {"name": '油烟机', "value": "LAMPBLACKMACHINE"},
          {"name": '电磁炉', "value": "ELECTROMAGNETICFURNACE"},
          {"name": '微波炉', "value": "MICROWAVEOVEN"}
        ],
        floorType:[
          {"name": '低楼层', "value": "LOW"},
          {"name": '中楼层', "value": "MEDIUM"},
          {"name": '高楼层', "value": "HIGH"}
        ],
        house: {
          id: "",
          name: "",
          area: "",
          bathroom: "",
          floor: "",
          room: "",
          hall: "",
          preImg: [],
          orientation: "",
          renovationType: "",
          des: "",
          houseImg: [],
          houseLayout: "",
          selected: "",
          settings: [],
          payMode: "",
          floorType: "",
          rent: "",
          communityId: ""
        },
        communitys: [],
        dialogVisible: false,
        dialogImageUrl: ''
      }
    },
    created() {
      this.getAllComunity()
      this.getList();
    },
    methods: {
      getList() {
        //查询列表
        if (!this.hasPerm('article:list')) {
          return
        }
        this.listLoading = true;
        this.api({
          url: "/houseResource/listHouseResource",
          method: "get",
          params: this.listQuery
        }).then(data => {
          this.listLoading = false;
          this.list = data.list;
          this.totalCount = data.totalCount;
        })
      },
      uploadHttp(e) {
        var file = e.file;
        if (!file) return;
        // 分片上传文件
        cos.putObject({
          Bucket: Bucket,
          Region: 'ap-beijing',
          Key: file.name,
          Body: file,
          onProgress: function (progressData, callback) {
            logger.log(JSON.stringify(progressData));

          },
        }, function (err, data) {
          console.log(err, data);
        });
        let that = this;
//下面这个方法是vue中获取你上传成功后的Url地址，别人可以通过这个地址来查看，下载你上传的文件
        cos.getObjectUrl({
          Key: file.name,
          Bucket: Bucket,
          Sign: false,
          Region: 'ap-beijing',
        }, function (err, data) {
          let l = {}
          l["url"] = data.Url
          l["uid"] = 0;
          that.house.houseImg.push(l);
        });
      },
      beforeAvatarUpload(file) {
        // const isJPEG = file.name.split('.')[1] === 'jpeg';
        // const isJPG = file.name.split('.')[1] === 'jpg';
        // const isPNG = file.name.split('.')[1] === 'png';
        // const isLt500K = file.size / 1024 / 500 < 2;
        // if (!isJPG && !isJPEG && !isPNG) {
        //   this.$message.error('上传图片只能是 JPEG/JPG/PNG 格式!');
        // }
        // if (!isLt500K) {
        //   this.$message.error('单张图片大小不能超过 500KB!');
        // }
        // return (isJPEG || isJPG || isPNG) && isLt500K;
        return true
      },
      handleRemove: function (file) {
        for (var i in this.house.houseImg) {
          if (this.house.houseImg[i].url == file.url) {
            this.house.houseImg.splice(i, 1);
            break
          }
        }
      },
      uploadHttp1(e) {
        var file = e.file;
        if (!file) return;
        // 分片上传文件
        cos.putObject({
          Bucket: Bucket,
          Region: 'ap-beijing',
          Key: file.name,
          Body: file,
          onProgress: function (progressData, callback) {
            logger.log(JSON.stringify(progressData));

          },
        }, function (err, data) {
          console.log(err, data);
        });
        let that = this;
//下面这个方法是vue中获取你上传成功后的Url地址，别人可以通过这个地址来查看，下载你上传的文件
        cos.getObjectUrl({
          Key: file.name,
          Bucket: Bucket,
          Sign: false,
          Region: 'ap-beijing',
        }, function (err, data) {
          let l = {}
          l["url"] = data.Url
          l["uid"] = 0;
          that.house.preImg=[]
          that.house.preImg[0]=l;
        });
      },
      beforeAvatarUpload1(file) {
        // const isJPEG = file.name.split('.')[1] === 'jpeg';
        // const isJPG = file.name.split('.')[1] === 'jpg';
        // const isPNG = file.name.split('.')[1] === 'png';
        // const isLt500K = file.size / 1024 / 500 < 2;
        // if (!isJPG && !isJPEG && !isPNG) {
        //   this.$message.error('上传图片只能是 JPEG/JPG/PNG 格式!');
        // }
        // if (!isLt500K) {
        //   this.$message.error('单张图片大小不能超过 500KB!');
        // }
        // return (isJPEG || isJPG || isPNG) && isLt500K;
        return true
      },
      handleRemove1: function (file) {
        this.house.preImg=[]
      },
      chooseItem:function(id){
        if(this.house.settings.indexOf(id)==-1){
          this.house.settings.push(id)
        }else{
          this.house.settings.splice(this.house.settings.indexOf(id), 1);
        }
      },
      getAllComunity() {
        this.api({
          url: "/community/all",
          method: "get"
        }).then(data => {
          this.communitys = data;
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
      },
      showUpdate($index) {
        //显示修改对话框
        this.house.id = this.list[$index].id;
        this.dialogStatus = "update"
        this.dialogFormVisible = true
      },
      createArticle() {
        //保存新房源
        this.api({
          url: "/houseResource/addHouseResource",
          method: "post",
          data: this.house
        }).then(() => {
          this.getList();
          this.dialogFormVisible = false
        })
      },
      updateArticle() {
        //修改房源
        this.api({
          url: "/article/updateArticle",
          method: "post",
          data: this.house
        }).then(() => {
          this.getList();
          this.dialogFormVisible = false
        })
      },
    }
  }
</script>
