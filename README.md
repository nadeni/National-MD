#National-MD
这是一款经典的坦克大战游戏，在游戏中我们把敌人和自己更改成了植物大战僵尸的人物，并增添许多自己的元素。
游戏的登陆界面，注册，找回用户名，密码，欢迎，游戏记录界面均为自己设计。游戏部分借鉴了坦克大战的思路，以及一些函数，但无复制粘贴。
游戏的数据库部分的sql文件在 ~\NationalMd\src\MySQL_Code 中名字为MySQL_Code。
游戏有个用户名为root的玩家，密码为123456可以选择任意关卡，游戏目前共有13的关卡。
游戏注册界面有多种检测，如果有一栏没填或者格式错误，则无法进行下一栏的填写。
在找回密码，用户名界面设置了自动返回功能，当用户找回密码用户名之后，如果10秒用户无人操作，将会自动返回登陆界面，并且为传参返回，会将用户用户名自动填写。
游戏记录界面玩家可以选择记录的对应号码数，填写并确认，即可读取上次游戏进度。
游戏目前设计为无限命数，玩家一旦死亡游戏将从新从本关开始，一旦通关便开始新的关卡。
游戏下一步改进计划，添加更多的道具，添加两人游戏模式，添加血量，命数，积分等等游戏记录，将游戏改进成联网游戏，玩家可以使用相同用户在不同端口玩游戏。