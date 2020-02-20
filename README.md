# 超市管理系统

## 更新

>2019-12-09
>
>* 新增删除商品类别同时会删除该类别的所有商品

## 数据库

用户表

t_user：

```sql
CREATE TABLE t_user (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(20) DEFAULT NULL,
  password varchar(20) DEFAULT NULL,
  ismanager tinyint(1) DEFAULT 0,
  PRIMARY KEY (id)
);

```

商品表：

t_good

```sql

CREATE TABLE t_good (
  id int(20) NOT NULL AUTO_INCREMENT,
  category varchar(20) NOT NULL,
  name varchar(20) NOT NULL,
  price float DEFAULT NULL,
  count int(20) DEFAULT NULL,
  state varchar(20) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY id (id)
);


```

商品类别表：

t_goodtype

```sql
CREATE TABLE t_goodType (
  id int(11) NOT NULL AUTO_INCREMENT,
  goodTypeName varchar(20) NOT NULL,
  goodTypeDesc varchar(1000) DEFAULT NULL,
  PRIMARY KEY (id)
)
```

操作记录表：

