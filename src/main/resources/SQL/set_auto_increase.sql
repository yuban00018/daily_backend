ALTER TABLE failed_todo ALTER COLUMN failed_id SET DEFAULT NEXTVAL('increase');
ALTER TABLE group_info ALTER COLUMN group_id SET DEFAULT NEXTVAL('increase');
ALTER TABLE plan ALTER COLUMN plan_id SET DEFAULT NEXTVAL('increase');
ALTER TABLE "user" ALTER COLUMN user_id SET DEFAULT NEXTVAL('increase');
ALTER TABLE user_group ALTER COLUMN record_id SET DEFAULT NEXTVAL('increase');
