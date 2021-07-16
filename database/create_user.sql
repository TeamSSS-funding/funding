use mysql;
create user mygoodsupporter@localhost identified by 'mygoodsupporter';
create database mygoodsupporter;
grant all privileges on mygoodsupporter.* to 'mygoodsupporter'@'localhost' identified by 'mygoodsupporter';