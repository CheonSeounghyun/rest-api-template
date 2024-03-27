-- PUBLIC.API_KEY definition

CREATE MEMORY TABLE "PUBLIC"."API_KEY"(
    "API_KEY_SN" NUMERIC NOT NULL,
    "API_KEY" VARCHAR(100) NOT NULL,
    "USE_YN" VARCHAR(1),
    "EXPR_YN" VARCHAR(1),
    "EXPR_DATE" VARCHAR(14),
    "REG_DATE" VARCHAR(14),
    "UPD_DATE" VARCHAR(14)
);

-- PUBLIC."MEMBER" definition

CREATE MEMORY TABLE "PUBLIC"."USER_INFO"(
    "USER_SN" NUMERIC NOT NULL,
    "USER_ID" VARCHAR(20) NOT NULL,
    "USER_PWD" VARCHAR(500),
    "USER_NM" VARCHAR(20),
    "USER_TEL" VARCHAR(20),
    "USE_YN" VARCHAR(1),
    "REG_DATE" VARCHAR(14),
    "UPD_DATE" VARCHAR(14)
);

CREATE SEQUENCE API_KEY_SN_SEQ START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE USER_SN_SEQ START WITH 1 INCREMENT BY 1;