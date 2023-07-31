# Çizilen bir dijital eğlence platformu ER diyagramının bir kısmının veritabanının oluşturulması, bu veritabanı üzerinde istenilen sorguların MySQL kullanarak ifade edilmesi amaçlanmıştır. 
 
![ER_platform](https://github.com/KutayTunaa/db_proje_1/assets/113691691/53d7a884-2bf0-452d-9020-bb24acfdac1a)


database scrpts:

CREATE DATABASE `digital_eglence_platformu` ;
USE `digital_eglence_platformu`;

CREATE TABLE `begenir` (
  `kullaniciID` int NOT NULL,
  `videoID` int NOT NULL,
  PRIMARY KEY (`kullaniciID`,`videoID`),
  KEY `videoID` (`videoID`),
  CONSTRAINT `begenir_ibfk_1` FOREIGN KEY (`kullaniciID`) REFERENCES `kullanici` (`kullaniciID`),
  CONSTRAINT `begenir_ibfk_2` FOREIGN KEY (`videoID`) REFERENCES `video` (`videoID`)
) ;


CREATE TABLE `kullanici` (
  `kullaniciID` int NOT NULL,
  `eposta` varchar(50) DEFAULT NULL,
  `sifre` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`kullaniciID`),
  UNIQUE KEY `eposta_UNIQUE` (`eposta`)
) ;

INSERT INTO `kullanici` VALUES (1,'ahmet@mail.com','123456'),(2,'mehmet@mail.com','abcdef'),(3,'ayse@mail.com','aaaaaa');
COMMIT;


CREATE TABLE `uyedir` (
  `kullaniciID` int NOT NULL,
  `uyelikID` int NOT NULL,
  PRIMARY KEY (`kullaniciID`,`uyelikID`),
  KEY `uyelikID` (`uyelikID`),
  CONSTRAINT `uyedir_ibfk_1` FOREIGN KEY (`kullaniciID`) REFERENCES `kullanici` (`kullaniciID`),
  CONSTRAINT `uyedir_ibfk_2` FOREIGN KEY (`uyelikID`) REFERENCES `uyelik` (`uyelikID`)
);

INSERT INTO `uyedir` VALUES (1,1),(2,2),(3,3);
COMMIT;


CREATE TABLE `uyelik` (
  `uyelikID` int NOT NULL,
  `ekranSayisi` int DEFAULT NULL,
  `goruntuKalitesi` varchar(20) DEFAULT NULL,
  `ucret` int DEFAULT NULL,
  PRIMARY KEY (`uyelikID`)
);
INSERT INTO `uyelik` VALUES (1,1,'HD',24),(2,2,'HD',32),(3,3,'4K',35);
COMMIT;

CREATE TABLE `video` (
  `videoID` int NOT NULL,
  `yonetmenID` int DEFAULT NULL,
  `yayinTarihi` int DEFAULT NULL,
  `tur` varchar(50) DEFAULT NULL,
  `rating` int DEFAULT NULL,
  `baslik` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`videoID`),
  KEY `idx_video_yayinTarihi` (`yayinTarihi`)
);
INSERT INTO `video` VALUES (1,1,2010,'Bilim Kurgu',9,'Inception'),(2,1,2014,'Bilim Kurgu',8,'Interstellar'),(3,2,2014,'Dram',7,'kis uykusu'),(4,2,2018,'Dram',9,'Ahlat agaci'),(5,3,1994,'SuÃ§',9,'Pulp Fiction');
COMMIT;

CREATE TABLE `yonetir` (
  `yonetmenID` int NOT NULL,
  `videoID` int NOT NULL,
  PRIMARY KEY (`yonetmenID`,`videoID`),
  KEY `videoID` (`videoID`),
  CONSTRAINT `yonetir_ibfk_1` FOREIGN KEY (`yonetmenID`) REFERENCES `yonetmen` (`yonetmenID`),
  CONSTRAINT `yonetir_ibfk_2` FOREIGN KEY (`videoID`) REFERENCES `video` (`videoID`)
);
INSERT INTO `yonetir` VALUES (1,1),(1,2),(2,3),(2,4),(3,5);
COMMIT;


CREATE TABLE `yonetmen` (
  `yonetmenID` int NOT NULL,
  `isim` varchar(50) DEFAULT NULL,
  `soyisim` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`yonetmenID`)
);
INSERT INTO `yonetmen` VALUES (1,'Christopher','Nolan'),(2,'Nuri Bilge','Ceylan'),(3,'Quentin','Tarantino'),(5,'Christopher','Nolan'),(6,'Nuri Bilge','Ceylan'),(7,'Quentin','Tarantino');
COMMIT;
