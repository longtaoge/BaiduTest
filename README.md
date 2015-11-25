# BaiduTest
Baidu 地图测试
### 如何将纹理折线添加到地图上 ###

百度地图开发过程中遇到了这样一坑，就是没有办法将自定义纹理折线的添加到地图上，一旦加上自定义纹理（textureIndexs）就会闪退，最可恨的是，百度官方给出的Dome 也有这个Bug.在这里说明一下，我的测试机是天语K-Touch-W95,是不是因为手机原因就不太清楚了，其实，闪退崩溃都不怕，最要命的是百度给出的Dome 里 GeometryDemo.java 这个类里面，有很多行代码，崩溃了还没有报异常信息！


![](http://i.imgur.com/XIvjPey.png)


实在没有办法，只能拿出看家本领，注释代码，逆行修复，经过一翻折腾，终于定位到了这么一段代码
    

    // 添加多纹理分段的折线绘制
		// 添加折线点列表
		LatLng p111 = new LatLng(39.865, 116.444);
		LatLng p211 = new LatLng(39.825, 116.494);
		LatLng p311 = new LatLng(39.855, 116.534);
		LatLng p411 = new LatLng(39.805, 116.594);
		List<LatLng> points11 = new ArrayList<LatLng>();
		points11.add(p111);
		points11.add(p211);
		points11.add(p311);
		points11.add(p411);
		// 添加纹理图片列表
		List<BitmapDescriptor> textureList = new ArrayList<BitmapDescriptor>();
		textureList.add(mRedTexture);
		textureList.add(mBlueTexture);
		textureList.add(mGreenTexture);
		// 添加纹理图片对应的顺序
		List<Integer> textureIndexs = new ArrayList<Integer>();
		textureIndexs.add(0);
		textureIndexs.add(1);
		textureIndexs.add(2);

		// 覆盖物构造方法 要注意
		// .textureIndex(textureIndexs).customTextureList(textureList)
		// 这两个方法的调用顺序不能换，否则会引起没有异常信息的崩溃
		OverlayOptions ooPolyline11 = new PolylineOptions().width(20)// 宽度
				.points(points11)// 点位座标列表
				.textureIndex(textureIndexs)// 点位纹理图片信息的顺序列表
				.customTextureList(textureList)// 纹理图片列表
		// .dottedLine(true)//是否是虚线 貌似 自定义纹理不支持此功能 ，会引起崩溃
		;// .textureIndex(textureIndexs);



经过反复注释，后来感觉这两行代码可能有问题：

    	.textureIndex(textureIndexs)// 点位纹理图片信息的顺序列表
		.customTextureList(textureList)// 纹理图片列表
  百度地图官方的示例中，.customTextureList(textureList)，调用在先，根据直觉来看，这两行代码顺序可能影响，死马当做活马医，只能试一试，果然，把两行代码的顺序换了之后，地图正常显示出来了，

但是，同样是这部测试机.dottedLine(true)//是否是虚线这个属性不能设置 貌似 自定义纹理不支持此功能 ，会引起崩溃，如果哪位朋友知道什么原因的可以交流一下。

[https://github.com/longtaoge/BaiduTest.git](https://github.com/longtaoge/BaiduTest.git)
