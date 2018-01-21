/*
 Navicat Premium Data Transfer

 Source Server         : mnk
 Source Server Type    : PostgreSQL
 Source Server Version : 90604
 Source Host           : localhost:5432
 Source Catalog        : mnk
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 90604
 File Encoding         : 65001

 Date: 21/01/2018 21:21:10
*/


-- ----------------------------
-- Sequence structure for increment
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."increment";
CREATE SEQUENCE "public"."increment" 
INCREMENT 1
MINVALUE  1
MAXVALUE 999999999999999
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for increment_index
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."increment_index";
CREATE SEQUENCE "public"."increment_index" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for increment_values
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."increment_values";
CREATE SEQUENCE "public"."increment_values" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Table structure for t_country
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_country";
CREATE TABLE "public"."t_country" (
  "uid" int4 NOT NULL,
  "name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "url" varchar(1000) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."t_country"."uid" IS 'Код страны';
COMMENT ON COLUMN "public"."t_country"."name" IS 'Наименование страны';
COMMENT ON COLUMN "public"."t_country"."url" IS 'Путь до файла с картинкой';

-- ----------------------------
-- Records of t_country
-- ----------------------------
INSERT INTO "public"."t_country" VALUES (2, 'Canada', '/icons/countries_icons/Canada.png');
INSERT INTO "public"."t_country" VALUES (5, 'Бразилия', '/icons/countries_icons/Brazil.png');
INSERT INTO "public"."t_country" VALUES (11, 'Ккатяленд', '/icons/countries_icons/UNESCO.png');

-- ----------------------------
-- Table structure for t_index
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_index";
CREATE TABLE "public"."t_index" (
  "uid" int4 NOT NULL,
  "name" varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."t_index"."uid" IS 'ИД Показателя';
COMMENT ON COLUMN "public"."t_index"."name" IS 'Название показателя';

-- ----------------------------
-- Records of t_index
-- ----------------------------
INSERT INTO "public"."t_index" VALUES (1, 'Показатель 1');
INSERT INTO "public"."t_index" VALUES (8, 'Мясо');
INSERT INTO "public"."t_index" VALUES (9, 'Пшено');
INSERT INTO "public"."t_index" VALUES (10, '956');

-- ----------------------------
-- Table structure for t_values
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_values";
CREATE TABLE "public"."t_values" (
  "uid" int4 NOT NULL,
  "country_uid" int4,
  "index_uid" int4,
  "value_x" float8,
  "value_y" float8
)
;

-- ----------------------------
-- Records of t_values
-- ----------------------------
INSERT INTO "public"."t_values" VALUES (6, 2, 1, 1, 8);
INSERT INTO "public"."t_values" VALUES (7, 2, 1, 2, 6);
INSERT INTO "public"."t_values" VALUES (8, 2, 1, 3, 10);
INSERT INTO "public"."t_values" VALUES (9, 2, 1, 4, 6);
INSERT INTO "public"."t_values" VALUES (10, 2, 1, 5, 10);
INSERT INTO "public"."t_values" VALUES (11, 2, 1, 6, 13);
INSERT INTO "public"."t_values" VALUES (12, 2, 1, 7, 9);
INSERT INTO "public"."t_values" VALUES (13, 2, 1, 8, 11);
INSERT INTO "public"."t_values" VALUES (14, 2, 1, 9, 15);
INSERT INTO "public"."t_values" VALUES (16, 2, 8, 13, 2.1);
INSERT INTO "public"."t_values" VALUES (18, 2, 8, 8, 14.27);
INSERT INTO "public"."t_values" VALUES (15, 2, 1, 10, 17);
INSERT INTO "public"."t_values" VALUES (19, 2, 10, 55, 655);
INSERT INTO "public"."t_values" VALUES (20, 2, 10, 88, 88);
INSERT INTO "public"."t_values" VALUES (21, 2, 10, 64, 354);
INSERT INTO "public"."t_values" VALUES (22, 2, 10, 56, 99);
INSERT INTO "public"."t_values" VALUES (23, 11, 8, 6, 90);
INSERT INTO "public"."t_values" VALUES (24, 11, 8, 8, 13);
INSERT INTO "public"."t_values" VALUES (25, 11, 8, 7, 14);
INSERT INTO "public"."t_values" VALUES (26, 11, 8, 10, 56);
INSERT INTO "public"."t_values" VALUES (27, 11, 8, 15, 24);
INSERT INTO "public"."t_values" VALUES (28, 11, 8, 21, 59);
INSERT INTO "public"."t_values" VALUES (29, 11, 8, 35, 100);
INSERT INTO "public"."t_values" VALUES (30, 11, 1, 15, 44);
INSERT INTO "public"."t_values" VALUES (31, 11, 1, 18, 14);
INSERT INTO "public"."t_values" VALUES (32, 11, 1, 19, 25);
INSERT INTO "public"."t_values" VALUES (33, 11, 1, 23, 46);
INSERT INTO "public"."t_values" VALUES (34, 11, 1, 25, 44);
INSERT INTO "public"."t_values" VALUES (35, 11, 1, 43, 11);
INSERT INTO "public"."t_values" VALUES (36, 11, 1, 59, 50);
INSERT INTO "public"."t_values" VALUES (37, 5, 1, 1, 5);
INSERT INTO "public"."t_values" VALUES (38, 5, 1, 2, 9);
INSERT INTO "public"."t_values" VALUES (39, 5, 1, 3, 6);
INSERT INTO "public"."t_values" VALUES (40, 5, 1, 4, 0);
INSERT INTO "public"."t_values" VALUES (41, 5, 1, 5, -99);
INSERT INTO "public"."t_values" VALUES (42, 5, 1, 6, 18);
INSERT INTO "public"."t_values" VALUES (43, 5, 1, 7, 10);
INSERT INTO "public"."t_values" VALUES (44, 5, 1, 8, 55);
INSERT INTO "public"."t_values" VALUES (45, 5, 1, 9, 40);
INSERT INTO "public"."t_values" VALUES (46, 5, 1, 10, 13);

-- ----------------------------
-- Function structure for add_country
-- ----------------------------
DROP FUNCTION IF EXISTS "public"."add_country"("name" varchar, "url" varchar);
CREATE OR REPLACE FUNCTION "public"."add_country"("name" varchar, "url" varchar)
  RETURNS "pg_catalog"."numeric" AS $BODY$

DECLARE
	future_uid int4;

BEGIN
	SELECT nextval('increment_index') INTO future_uid;
	insert into t_country(uid, name, url) values(future_uid, name, url);
	return future_uid;
END; $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

-- ----------------------------
-- Function structure for add_index
-- ----------------------------
DROP FUNCTION IF EXISTS "public"."add_index"("future_name" varchar);
CREATE OR REPLACE FUNCTION "public"."add_index"("future_name" varchar)
  RETURNS "pg_catalog"."void" AS $BODY$

declare
	future_uid int4;


BEGIN
  SELECT nextval('increment_index') INTO future_uid;
	insert into t_index(uid, name) values(future_uid, future_name);
END; $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

-- ----------------------------
-- Function structure for add_values
-- ----------------------------
DROP FUNCTION IF EXISTS "public"."add_values"("p_uid_country" int4, "p_uid_index" int4, "p_value_x" float8, "p_value_y" float8);
CREATE OR REPLACE FUNCTION "public"."add_values"("p_uid_country" int4, "p_uid_index" int4, "p_value_x" float8, "p_value_y" float8)
  RETURNS "pg_catalog"."void" AS $BODY$

DECLARE
    future_uid int8;

BEGIN
	SELECT nextval('increment_values') INTO future_uid;
	insert into t_values (uid, country_uid, index_uid, value_x, value_y) 
	VALUES(future_uid, p_uid_country, p_uid_index, p_value_x, p_value_y);
END; $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

-- ----------------------------
-- Function structure for get_values
-- ----------------------------
DROP FUNCTION IF EXISTS "public"."get_values"("p_uid_country" int4, "p_uid_index" int4);
CREATE OR REPLACE FUNCTION "public"."get_values"("p_uid_country" int4, "p_uid_index" int4)
  RETURNS "pg_catalog"."refcursor" AS $BODY$

DECLARE
	res refcursor;

BEGIN
	open res for select value_x, value_y, uid from t_values where country_uid = p_uid_country and index_uid = p_uid_index;
	return res;
END; $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

-- ----------------------------
-- Function structure for remove_country
-- ----------------------------
DROP FUNCTION IF EXISTS "public"."remove_country"("p_uid" int4);
CREATE OR REPLACE FUNCTION "public"."remove_country"("p_uid" int4)
  RETURNS "pg_catalog"."void" AS $BODY$

BEGIN
  delete from t_values  where country_uid = p_uid;
	delete from t_country where uid = p_uid;
END; $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

-- ----------------------------
-- Function structure for remove_index
-- ----------------------------
DROP FUNCTION IF EXISTS "public"."remove_index"("p_uid" int4);
CREATE OR REPLACE FUNCTION "public"."remove_index"("p_uid" int4)
  RETURNS "pg_catalog"."void" AS $BODY$

BEGIN
  delete from t_index  where uid = p_uid;
	delete from t_values where index_uid = p_uid;
END; $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

-- ----------------------------
-- Function structure for remove_values
-- ----------------------------
DROP FUNCTION IF EXISTS "public"."remove_values"("p_uid" int4);
CREATE OR REPLACE FUNCTION "public"."remove_values"("p_uid" int4)
  RETURNS "pg_catalog"."void" AS $BODY$

BEGIN
	delete from t_values where uid = p_uid;
END; $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

-- ----------------------------
-- Function structure for update_values
-- ----------------------------
DROP FUNCTION IF EXISTS "public"."update_values"("p_uid" int4, "p_value_x" float8, "p_value_y" float8);
CREATE OR REPLACE FUNCTION "public"."update_values"("p_uid" int4, "p_value_x" float8, "p_value_y" float8)
  RETURNS "pg_catalog"."void" AS $BODY$

BEGIN
	update t_values set value_x = p_value_x, value_y = p_value_y where uid = p_uid;
END; $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."increment"
OWNED BY "public"."t_country"."uid";
SELECT setval('"public"."increment"', 15, true);
SELECT setval('"public"."increment_index"', 12, true);
SELECT setval('"public"."increment_values"', 47, true);

-- ----------------------------
-- Uniques structure for table t_country
-- ----------------------------
ALTER TABLE "public"."t_country" ADD CONSTRAINT "uq_uid" UNIQUE ("uid");

-- ----------------------------
-- Primary Key structure for table t_index
-- ----------------------------
ALTER TABLE "public"."t_index" ADD CONSTRAINT "t_index_pkey" PRIMARY KEY ("uid");

-- ----------------------------
-- Primary Key structure for table t_values
-- ----------------------------
ALTER TABLE "public"."t_values" ADD CONSTRAINT "t_values_pkey" PRIMARY KEY ("uid");

-- ----------------------------
-- Foreign Keys structure for table t_values
-- ----------------------------
ALTER TABLE "public"."t_values" ADD CONSTRAINT "fk_country" FOREIGN KEY ("country_uid") REFERENCES "public"."t_country" ("uid") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."t_values" ADD CONSTRAINT "fk_index" FOREIGN KEY ("index_uid") REFERENCES "public"."t_index" ("uid") ON DELETE NO ACTION ON UPDATE NO ACTION;
