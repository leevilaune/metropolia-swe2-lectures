-- ----------------------------------------------------
-- Create tables
-- ----------------------------------------------------

CREATE TABLE language (
    id VARCHAR(5) PRIMARY KEY
);

CREATE TABLE uistring (
    id VARCHAR(50) PRIMARY KEY
);

CREATE TABLE uistring_translation (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    uiStringId VARCHAR(50) NOT NULL,
    language VARCHAR(5) NOT NULL,
    string VARCHAR(255),
    CONSTRAINT fk_uiString FOREIGN KEY (uiStringId) REFERENCES uistring(id),
    CONSTRAINT fk_language FOREIGN KEY (language) REFERENCES language(id)
);

-- ----------------------------------------------------
-- Insert languages
-- ----------------------------------------------------
INSERT INTO language (id) VALUES ('en');
INSERT INTO language (id) VALUES ('fa');
INSERT INTO language (id) VALUES ('fr');
INSERT INTO language (id) VALUES ('ja');

-- ----------------------------------------------------
-- Insert UI strings
-- ----------------------------------------------------
INSERT INTO uistring (id) VALUES ('label-distance');
INSERT INTO uistring (id) VALUES ('label-fuel');
INSERT INTO uistring (id) VALUES ('label-calculate');
INSERT INTO uistring (id) VALUES ('label-title');
INSERT INTO uistring (id) VALUES ('label-consumption');
INSERT INTO uistring (id) VALUES ('error-invalid-input');

-- ----------------------------------------------------
-- Insert translations for English
-- ----------------------------------------------------
INSERT INTO uistring_translation (uiStringId, language, string) VALUES ('label-distance','en','distance');
INSERT INTO uistring_translation (uiStringId, language, string) VALUES ('label-fuel','en','fuel');
INSERT INTO uistring_translation (uiStringId, language, string) VALUES ('label-calculate','en','calculate');
INSERT INTO uistring_translation (uiStringId, language, string) VALUES ('label-title','en','Fuel Calculator');
INSERT INTO uistring_translation (uiStringId, language, string) VALUES ('label-consumption','en','Consumption');
INSERT INTO uistring_translation (uiStringId, language, string) VALUES ('error-invalid-input','en','Invalid input');

-- ----------------------------------------------------
-- Insert translations for Persian (Farsi)
-- ----------------------------------------------------
INSERT INTO uistring_translation (uiStringId, language, string) VALUES ('label-distance','fa','فاصله');
INSERT INTO uistring_translation (uiStringId, language, string) VALUES ('label-fuel','fa','سوخت');
INSERT INTO uistring_translation (uiStringId, language, string) VALUES ('label-calculate','fa','محاسبه');
INSERT INTO uistring_translation (uiStringId, language, string) VALUES ('label-title','fa','محاسبه‌گر مصرف سوخت');
INSERT INTO uistring_translation (uiStringId, language, string) VALUES ('label-consumption','fa','مصرف');
INSERT INTO uistring_translation (uiStringId, language, string) VALUES ('error-invalid-input','fa','ورودی نامعتبر');

-- ----------------------------------------------------
-- Insert translations for French
-- ----------------------------------------------------
INSERT INTO uistring_translation (uiStringId, language, string) VALUES ('label-distance','fr','distance');
INSERT INTO uistring_translation (uiStringId, language, string) VALUES ('label-fuel','fr','carburant');
INSERT INTO uistring_translation (uiStringId, language, string) VALUES ('label-calculate','fr','calculer');
INSERT INTO uistring_translation (uiStringId, language, string) VALUES ('label-title','fr','Calculateur de consommation');
INSERT INTO uistring_translation (uiStringId, language, string) VALUES ('label-consumption','fr','consommation');
INSERT INTO uistring_translation (uiStringId, language, string) VALUES ('error-invalid-input','fr','entrée invalide');

-- ----------------------------------------------------
-- Insert translations for Japanese
-- ----------------------------------------------------
INSERT INTO uistring_translation (uiStringId, language, string) VALUES ('label-distance','ja','距離');
INSERT INTO uistring_translation (uiStringId, language, string) VALUES ('label-fuel','ja','燃料');
INSERT INTO uistring_translation (uiStringId, language, string) VALUES ('label-calculate','ja','計算');
INSERT INTO uistring_translation (uiStringId, language, string) VALUES ('label-title','ja','燃費計算機');
INSERT INTO uistring_translation (uiStringId, language, string) VALUES ('label-consumption','ja','消費量');
INSERT INTO uistring_translation (uiStringId, language, string) VALUES ('error-invalid-input','ja','無効な入力');