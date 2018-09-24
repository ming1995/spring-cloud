var http = require('http');
var url=require('url');
var path=require('path');
var server=http.createServer(function (req, res) {
    var pathname=url.parse(req.url).pathname;
    // 发送 HTTP 头部
    // HTTP 状态值: 200 : OK
    // 内容类型: text/plain
    res.writeHead(200, {'Content-Type':'application/json;charset=utf-8'});
    if(pathname=='/'){
        res.end(JSON.stringify({'index':"欢迎来到首页"}));
    }else if(pathname=="/health.json"){
        res.end(JSON.stringify({'status':"UP"}));
    }else{
        res.end('404');
    }
});

server.listen(8060,function () {
   console.log('listen on localhost:8060')
});