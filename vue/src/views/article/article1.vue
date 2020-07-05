<template>
  <div class="content">
    <input id="file" type="file" @change='shangchuan($event)'>
  </div>
</template>

<script>
  var Bucket ='leileizufang-1302428949';//腾讯云对象储存桶名
  var Region = 'ap-beijing';//对象储存你所处的地区编号
  var cos = new COS({
    getAuthorization: function (options,callback) {
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
    data(){
      return{
      }
    },
    mounted:function(){
    },
    methods:{
      shangchuan(e){
        var file = e.target.files[0];
        if (!file) return;
        // 分片上传文件
        cos.putObject({
          Bucket: Bucket,
          Region: 'ap-beijing',
          Key: file.name,
          Body: file,
          onProgress: function (progressData,callback) {
            logger.log(JSON.stringify(progressData));
          },
        }, function(err, data) {
// console.log(err,data);
        });
//下面这个方法是vue中获取你上传成功后的Url地址，别人可以通过这个地址来查看，下载你上传的文件
        cos.getObjectUrl({
          Key: file.name,
          Bucket: Bucket,
          Sign: false,
          Region: 'ap-beijing',
        }, function (err, data) {
          console.log(data.Url);
        });
      },
    },
  }

</script><style scoped>
</style>
