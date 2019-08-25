import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import "package:flutter/widgets.dart";
import 'package:flutter/services.dart';
import 'package:flutter_module/util/toast_util.dart';
import 'package:flutter_module/util/navigator_util.dart';


class Test extends StatefulWidget{

  final String content;//wowId
  Test({this.content});

  _TestState createState() => _TestState();

}

class _TestState extends State<Test>{

  @override
  void initState(){
    super.initState();
    print('content:' + widget.content);
  }

  @override
  Widget build(BuildContext context){
    return MaterialApp(
      home:new Scaffold(
        appBar: new AppBar(
          brightness: Brightness.light,
          title: new Text(
            'Flutter',
            style: new TextStyle(fontSize: 20,color:Color(0xFF1A1A1A)),

          ),
          centerTitle: true,
          elevation: 0,
          backgroundColor: Colors.blue,
          leading: new IconButton(
            icon:new Icon(Icons.arrow_back),
            color:Color(0xFF333333),
            onPressed: (){
              closeFlutter(context);
            },
          ),
        ),
        body: new Container(
          color: Colors.white,
          child: new ListView(
            children: <Widget>[
              new Padding(padding: EdgeInsets.only(top:100)),
              new Container(
                alignment: Alignment.center,
                child: new Text(
                  widget.content,
                  style: new TextStyle(
                    color: Colors.red,
                    fontSize: 40,
                    fontWeight: FontWeight.normal,
                    decoration: TextDecoration.none
                  ),
                ),
              ),

              new Container(
                width: 100,
                alignment: Alignment.center,
                child: new MaterialButton(
                  child: new Text("打开原生的toast"),
                  color: Colors.greenAccent,
                  onPressed: (){
                    buttonClick();
                  }),
              ),
            ],
          ),
        ),
      ),
    );
  }


  //弹出toast
  void buttonClick(){
    ToastUtil.showToastInfo("哈哈哈");
  }


  //返回页面
  void closeFlutter(BuildContext context){
    NavigatorUtil.close(context);

  }




}

