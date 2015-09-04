(function (global) {

    var API={

        domain:'http://codestudy.sinaapp.com/api/',

        getList:function(service, action, moduleId, categoryId, childCategoryId, pageIndex, pageSize, orderBy, callback){
            SmartNativeAPI.httpPost(this.domain+ service,{action:action, m:moduleId, c: categoryId, s:childCategoryId, i:pageIndex,p:pageSize,o:orderBy},function (response) {
                console.log('>>>getList response:' + JSON.stringify(response));
                callback(response);
            });
        },

        getDetail:function(id, callback){
            SmartNativeAPI.httpPost(this.domain+ 'detail.php',{action:'detail', id: id},function (response) {
                console.log('>>>getDetail response:' + JSON.stringify(response));
                callback(response);
            });
        }

    };


    global.API=API;

})(window);
