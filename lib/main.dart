//The code doesn't compile.
//The paths that were edited to use JAVA / Kotlin for android or Objective C / Swift
//Those are android/app/src/main/java/com/MainActivity.java and 
//The issue in MainActivity got solved, but it only works for Lollipop and newer.
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Native Code',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(title: 'Native Code'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  @override
  int _batteryLevel = -1;
  Future<void> _getBatteryLevel() async{
    //The String that looks likea URL it's just a convention for the identifier.
    const platform = MethodChannel('course.futter.dev/battery');
    //Arguments can be added with a coma.
    try{
      final batteryLevel = await platform.invokeMethod('getBatteryLevel');
      setState(() {
        _batteryLevel = batteryLevel;
      });
    } on PlatformException catch(e){
      setState(() {
        _batteryLevel = -1;
      });
    }
  }

  void initState() {
    super.initState();
    _getBatteryLevel();
  }

  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text(
              'Battery Level: $_batteryLevel',
            ),
          ],
        ),
      ),
    );
  }
}
