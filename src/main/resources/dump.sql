/*
 Navicat PostgreSQL Data Transfer

 Source Server         : mnk
 Source Server Type    : PostgreSQL
 Source Server Version : 90604
 Source Host           : localhost:5432
 Source Catalog        : mnk
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 90604
 File Encoding         : 65001

 Date: 11/09/2017 22:43:45
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
  "uid" int4 NOT NULL DEFAULT NULL,
  "name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL DEFAULT NULL,
  "url" varchar(1000) COLLATE "pg_catalog"."default" DEFAULT NULL
)
;
COMMENT ON COLUMN "public"."t_country"."uid" IS 'Код страны';
COMMENT ON COLUMN "public"."t_country"."name" IS 'Наименование страны';
COMMENT ON COLUMN "public"."t_country"."url" IS 'Путь до файла с картинкой';

-- ----------------------------
-- Records of t_country
-- ----------------------------
INSERT INTO "public"."t_country" VALUES (2, 'Canada', 'http://www.canada.ru/');

-- ----------------------------
-- Table structure for t_index
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_index";
CREATE TABLE "public"."t_index" (
  "uid" int4 NOT NULL DEFAULT NULL,
  "name" varchar(255) COLLATE "pg_catalog"."default" DEFAULT NULL
)
;
COMMENT ON COLUMN "public"."t_index"."uid" IS 'ИД Показателя';
COMMENT ON COLUMN "public"."t_index"."name" IS 'Название показателя';

-- ----------------------------
-- Records of t_index
-- ----------------------------
INSERT INTO "public"."t_index" VALUES (1, 'Показатель 1');

-- ----------------------------
-- Table structure for t_values
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_values";
CREATE TABLE "public"."t_values" (
  "uid" int4 NOT NULL DEFAULT NULL,
  "country_uid" int4 DEFAULT NULL,
  "index_uid" int4 DEFAULT NULL,
  "value_x" float8 DEFAULT NULL,
  "value_y" float8 DEFAULT NULL
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
INSERT INTO "public"."t_values" VALUES (15, 2, 1, 10, 17);

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
SELECT setval('"public"."increment_index"', 3, true);
SELECT setval('"public"."increment_values"', 16, true);

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
ALTER TABLE "public"."t_values" ADD CONSTRAINT "fk_country" FOREIGN KEY ("country_uid") REFERENCES "t_country" ("uid") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."t_values" ADD CONSTRAINT "fk_index" FOREIGN KEY ("index_uid") REFERENCES "t_index" ("uid") ON DELETE NO ACTION ON UPDATE NO ACTION;
