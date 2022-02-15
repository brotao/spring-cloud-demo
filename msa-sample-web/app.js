
var express = require('express');

var port = 1234;

var app = express();
app.use(express.static('public'));
app.all('*', function (req, res) {
    console.log('123123123')
    if (req.path == '/favicon.ico') {
        res.end();
        return ;
    }

    //获取服务名称
    var serviceName = req.get('Service-Name');
    console.log('serviceName: %s', serviceName);
    if (!serviceName) {
        console.log('Service-Name request header is not exist');
        res.end();
        return;
    }
    // todo

});

app.listen((port, function () {
    console.log('server is running at %d', port)
}))
