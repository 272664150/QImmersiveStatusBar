# QImmersiveStatusBar
Android4.4~8.1状态栏适配


一、状态栏着色
-----
API<19：不执行<br>
API=19：黑灰渐进透明的状态栏<br>
API>=20：系统会自动生成一个半透明的状态栏。当API>=21时，能够调用系统API直接对状态栏着色

1.沉浸式模式
-----
StatusBarUtils.setColorBar(activity, barColor);<br>
系统状态栏与标题栏、ActionBar、ToolBar颜色相同。

2.透明状态栏
-----
StatusBarUtils.setTransparentBar(this);<br>
透明化系统状态栏，使得布局侵入状态栏的下面。


二、深浅字体
-----
StatusBarUtils.setStatusBarMode(this, darkMode);<br>
API<=22：原生不支持，Flyme>=4支持，MIUI 6~9支持<br>
API>=23：原生支持字体、图标深浅模式。MIUI开发版7.7.13及以后版本采用了系统API，旧方法无效但不会报错<br><br>
![gif效果图](https://github.com/272664150/QImmersiveStatusBar/blob/master/screenshots/1.gif)
