
# AutDataMotionWeb

--------------------------------  
##2016-11-11 22:57  
* 更新配置加载代码
* 云飞更新数据统计后台代码
* 秦曼更新FTP下载代码

--------------------------------  
##2016-11-10 20:13  
* 添加配置加载代码
* 云飞添加数据统计后台代码
* 秦曼添加FTP下载代码

--------------------------------  
##2016-11-09 21:30  
* 添加启动流程，后台工作框架代码
* 云飞添加数据统计前台
* 秦曼添加工作线程监控页面

--------------------------------  
##2016-11-08 11:12  
* 增加启动类RunMain.class
* 云飞使用动态SQL前后台传输Json数据
* 秦曼更新Download前台
* 张康更新产品树展开形式

--------------------------------  
##2016-11-07 18:51  
* 更新了了树形结构查询SQL语句
* 云飞更新动态分页，只在前台分页
* 秦曼完成Download前台

--------------------------------  
##2016-11-04 14:46  
* 修改了树形结构查询SQL语句
* 云飞完成的前台和后台动态分页，
* 张康update InitTree

--------------------------------  
##2016-11-03 11:27  
* 修改了压缩产品树
* merge 云飞完成的backup
* merge 秦曼 download

--------------------------------  
##2016-11-02 19:16  
* 修改了archive.html
* fixed conflicts of css html
* merge 云飞完成的backup.html
* merge 张康treemode
* 一个可用的前台版本

--------------------------------  
##2016-11-01 14:39  
* 修改了设计WebPages，更新到doc文件夹下
* 添加了后端抽象类
* 添加了前台公共框架页和树形结构页

--------------------------------  
##2016-10-28 16:12  
* 添加了前台框架页面
* 添加了后端接口类

--------------------------------  
##2016-10-26 22:28  
* 添加了数据库表相关的框架代码
* 创建了各自的模块工作文件夹

--------------------------------  
##2016-10-26 10:22
* 更新了设计doc，请查看数据库设计和流程设计
* 流程设计根据自身需求进行修改和包装
* 数据库表请将各自的建好

---------------------------------
##导入项目  
* 导入依赖项目  
UtilZW  
DUCRPC（需要安装zeroc-ice,eclipse 配置slice）  
AutDataMotion  

##教程说明
* 教程文档在doc目录下，请先查看jfinal-2.0-manual.pdf beetl-guide.pdf  

##项目启动
* 使用Application 启动com.platform.config.run 下的jfinalConfig.java
* 访问地址：http://127.0.0.1:100/jf/platform/index 用户名：admins 密码：000000