
create user if not exists 'javaExecuter'@'localhost' IDENTIFIED BY 'javaExecuter';
grant all PRIVILEGES on *.* to 'javaExecuter'@'localhost' IDENTIFIED BY 'javaExecuter';


create database if not exists dungeonrun;

create table if not exists dungeonrun.Player (
	PlayerId INT not null primary key auto_increment,
	#CONSTRAINT player_pk PRIMARY KEY (playerId),
	PlayerClassId int not null,
	PlayerName varchar(100) not null,
	#Experience int not null, 
	Currency int not null, 
	#Level int not null,
	#MonsterKilled int not null,
	#BelongsToPartyId varchar(100) not null,
	RegistrationDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
 	ChangeDate TIMESTAMP NOT NULL
                           DEFAULT CURRENT_TIMESTAMP 
                           ON UPDATE CURRENT_TIMESTAMP,
   ChangeDateTime TIMESTAMP NOT NULL
                           DEFAULT CURRENT_TIMESTAMP 
                           ON UPDATE CURRENT_TIMESTAMP
                         
);

	
create table if not exists dungeonrun.Class (
		
	classId int primary key auto_increment not null,
	className varchar(100) not null,
	MaxHP int not null,
	Damage INT not null,
	MaxResource int not null,
	BaseStrength int not null,
	BaseAgility int not null,
	BaseIntellect int not null,
	BaseDefence int not null,
	Initiative int not null,
	#IsDead boolean not null,
	#BelongsToPartyId varchar(100) not null,
	RegistrationDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
 	ChangeDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  
);


create table if not exists dungeonrun.PlayerActiveClass (

	PlayerActiveClassId int primary key auto_increment not null,
	playerId int not null, 
	foreign key (PlayerId) references dungeonrun.Player(PlayerId) on delete cascade,
	PlayerClassId int not null,
	ClassId int not null,
	foreign key (ClassId) references dungeonrun.Class(ClassId) on delete cascade,
	HP int not null,
	Damage int not null,
	Resource int not null,
	Strength int not null,
	Agility int not null,
	Intellect int not null,
	Defence int not null,
	Initiative int not null,
	level int not null,
	Experience int not null, 
	#Currency int not null
	MonsterKilled int not null, 
	isDead boolean not null,
	RegistrationDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
 	ChangeDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  
);


create table if not exists dungeonrun.PlayerParty (

	PlayerPartyId int primary key auto_increment not null,
	PlayerId int not null,
	foreign key (PlayerId) references dungeonrun.Player(PlayerId) on delete cascade,
	BelongsToPartyId varchar(100) not null,
	RegistrationDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
	
);



create table if not exists dungeonrun.Item (
		
	ItemId INT primary key not null auto_increment,
 	ItemName varchar(100) not null,
 	ItemDamage int, 
 	ItemInitiative int, 
 	ItemLevelLock int,
 	ItemDefence int,
 	ItemBlock int, 
 	RegistrationDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
 	ChangeDate TIMESTAMP NOT NULL
                           DEFAULT CURRENT_TIMESTAMP 
                           ON UPDATE CURRENT_TIMESTAMP,
   ChangeDateTime TIMESTAMP NOT NULL
                           DEFAULT CURRENT_TIMESTAMP 
                           ON UPDATE CURRENT_TIMESTAMP
);



create table if not exists dungeonrun.PlayerInventory (

	PlayerInventoryId int primary key not null auto_increment,
	PlayerId int not null,
	foreign key (PlayerId) references dungeonrun.Player(PlayerId) on delete cascade,
	Itemid int not null,
	foreign key (ItemId) references dungeonrun.Item(ItemId) on delete cascade,
	PlayerClassId int not null,
	ItemSlot int,
	ItemPrice int,
	#BelongsToPartyId varchar(100) not null,
	RegistrationDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
	
);

create table if not exists dungeonrun.Monster (
	
	MonsterId INT primary key not null auto_increment,
	MonsterType varchar(100) NOT null,
	MonsterName varchar(100) not null,
	MonsterHP int not null,
	MonsterResource int not null,
	MonsterDamage int not null,
	MonsterInititaive int not null,
	MonsterDefence int not null,
	MonsterStrength int not null,
	MonsterAgility int not null,
	MonsterIntellect int not null,
	MonsterIsDead boolean not null,
	MonsterGivesExperience int not null,
	MonsterGivesCurrency int not null,
	RegistrationDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
 	ChangeDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   	ChangeDateTime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
	
);



create table if not exists dungeonrun.Map (

	MapId INT primary key not null auto_increment,
	MapName varchar(50) not null	

);



create table if not exists dungeonrun.MonsterPlayerFight (

	MonsterPlayerid int primary key auto_increment not null,
	PlayerId int not null,
	foreign key (PlayerId) references dungeonrun.player(PlayerId) on delete cascade,
	MonsterId int not null,
	foreign key (MonsterId) references dungeonrun.monster(MonsterId) on delete cascade,
	MapId int not null,
	foreign key (MapId) references dungeonrun.map(MapId) on delete cascade, 
	AttackingUnit varchar(100) not null,
	Attacked varchar(100) not null,
	MonsterFightId int not null,
	TypeOfAbility varchar(100) not null,
	DamageDone int,
	HealthLeft int not null,
	HasFled boolean not null,
	GameRound int not null,
	BattleId varchar(100) not null,
	#CurrentLevel int not null,
	#FacingType varchar(200) not null,
	
	RegistrationDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
 	ChangeDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
	
);

create table if not exists dungeonrun.playerSave (

	playerSaveId int primary key auto_increment not null,
	PlayerId int not null,
	foreign key (PlayerId) references dungeonrun.player(PlayerId) on delete cascade,
	SaveSlotName varchar(100) not null,
	RegistrationDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
	
);



create table if not exists dungeonrun.MapLevelCompleted (
	
	MapLevelCompletedId int primary key not null auto_increment,
	#MapLevelNewGamePlus int not null,
	PlayerId int not null,
	foreign key (PlayerId) references dungeonrun.player(PlayerId) on delete cascade,
	MonsterId int not null,
	foreign key (MonsterId) references dungeonrun.monster(MonsterId) on delete cascade,
	MapId int not null,
	foreign key (MapId) references dungeonrun.map(MapId) on delete cascade, 
	HasPartyBeatenLevel boolean not null,
	RegistrationDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP

);

