/*
reference:
https://opengauss.org/zh/docs/latest/docs/BriefTutorial/触发器.html
author:
曹卓文
date:
2022/5/16
func:
对经验超过5的用户，对超过5的部分进行翻倍。如今日经验收益为6，则增加7点经验。
*/
CREATE OR REPLACE FUNCTION tri_bonus_func() RETURNS TRIGGER AS
$$
DECLARE
BEGIN
	UPDATE "user" set "exp" = 2 * NEW."exp" - OLD."exp" - 5
	WHERE user_id = NEW.user_id;
	RETURN NEW;
END
$$ LANGUAGE PLPGSQL;

CREATE TRIGGER TRIG1
AFTER UPDATE ON "user"
FOR EACH ROW
WHEN (NEW."exp" - 5 > OLD."exp")
	EXECUTE PROCEDURE tri_bonus_func();

/*DROP TRIGGER TRIG1 ON "user";*/