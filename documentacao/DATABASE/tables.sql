--------------------------------------------------------
--  CREATE SQUEMA
--------------------------------------------------------

-- ALTER SESSION SET "_ORACLE_SCRIPT" = true;

-- CREATE USER musicstore IDENTIFIED BY root;

-- GRANT ALL PRIVILEGES TO musicstore;


--------------------------------------------------------
--  DDL for Table TB_GENRE
--------------------------------------------------------

CREATE SEQUENCE artist_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE "TB_ARTISTA" (
                              "ID_ARTISTA" NUMBER(19,0) DEFAULT artist_sequence.NEXTVAL PRIMARY KEY,
                              "NM_ARTISTA" VARCHAR2(255 CHAR),
                              "NACIONALIDADE" VARCHAR2(255 CHAR)
);



CREATE UNIQUE INDEX  "IDX_TB_ARTISTA_ID" ON  "TB_ARTISTA" ("ID_ARTISTA");
--------------------------------------------------------
--  Constraints for Table TB_ARTIST
--------------------------------------------------------

ALTER TABLE  "TB_ARTISTA" MODIFY ("ID_ARTISTA" NOT NULL ENABLE);
ALTER TABLE  "TB_ARTISTA" ADD PRIMARY KEY ("ID_ARTISTA");

--------------------------------------------------------
--  DDL for Table TB_MOVIE
--------------------------------------------------------

CREATE TABLE  "TB_MUSIC"
(	"ID_MUSIC" NUMBER(19,0),
     "TITLE" VARCHAR2(255 CHAR),
     "ARTIST" NUMBER(19,0),
     "STYLE" VARCHAR2(255 CHAR),
     "DURATION" VARCHAR2(255 CHAR),
     "ORIGINAL_LANGUAGE" VARCHAR2(255 CHAR),
     "EXPLICIT_LIRICS" NUMBER(1,0)
   ) ;


CREATE UNIQUE INDEX  "IDX_TB_MUSIC_ID" ON  "TB_MUSIC" ("ID_MUSIC");
--------------------------------------------------------
--  Constraints for Table TB_MUSIC
--------------------------------------------------------

ALTER TABLE  "TB_MUSIC" MODIFY ("ID_MUSIC" NOT NULL ENABLE);
ALTER TABLE  "TB_MUSIC" MODIFY ("EXPLICIT_LIRICS" NOT NULL ENABLE);
ALTER TABLE  "TB_MUSIC" ADD CHECK (EXPLICIT_LIRICS in (0,1)) ENABLE;
ALTER TABLE  "TB_MUSIC" ADD PRIMARY KEY ("ID_MUSIC");
--------------------------------------------------------
--  Ref Constraints for Table TB_MUSIC
--------------------------------------------------------

ALTER TABLE  "TB_MUSIC" ADD CONSTRAINT "TB_MUSIC_FK_ARTIST" FOREIGN KEY ("ARTIST")
    REFERENCES  "TB_ARTISTA" ("ID_ARTISTA") ENABLE;

--------------------------------------------------------
--  DDL for Sequence SQ_ARTIST
--------------------------------------------------------

CREATE SEQUENCE  "SQ_ARTIST"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;

--------------------------------------------------------
--  DDL for Sequence SQ_MUSIC
--------------------------------------------------------

CREATE SEQUENCE  "SQ_MUSIC"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
