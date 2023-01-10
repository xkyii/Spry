# SpryAdmin
BackEnd with Quarkus matches AgileBoot(Ruoyi)'s FrontEnd

## 数据库

### 数据库(MySql8)
```sql
-- 登录为root
use mysql;

-- 创建数据库
create database spry DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- 提权(给xkyii)
grant all privileges on spry.* to 'xkyii'@'%';
flush privileges;
```

## 命令

### 环境变量
```powershell
# 指定java11目录
$env:JAVA_HOME="D:\Scoop\apps\openjdk11\current"

# 指定quarkus.bat中的java命令版本
$env:JAVACMD=$env:JAVA_HOME + '\bin\java.exe'

# 指定maven目录
$env:M2_HOME='D:\Scoop\apps\maven\3.8.6'

# mvn指定settings
mvn clean package --settings F:\Maven\settings.xml -Dmaven.test.skip=true
```

### 调试
```shell
quarkus dev
```