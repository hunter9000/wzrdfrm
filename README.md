WZRD FRM!

farm plots, seeds to plant, let them grow, harvest
harvest to get seeds, other mats, xp orbs, unlock orbs
fertilizers, boosters, etc can be used when planting
choose your class, get abilities gated by level that only work when that class is equipped
get permanent bonuses once max level in a class
special mats let you unlock other classes
combinations of seeds + mats can have special effects
classes have dependencies on other classes

unlock farm plots as you go, with plot unlock orbs

if you switch classes with stuff planted, whatever you are when you harvest gains xp


plants:
scrubweeds
ferns
food
grass
trees
water plants (clams, kelp)
flowers
berries
mushrooms
crystals

plants defined in db
    WEEDS,
    GRASS,
    FERN,
    POTATO,
    STRAWBERRY,
    LILY,
    CATTAIL,
grow time, list of resources granted and probabilities? or define probs separately for resource type? x% for seeds, y% for orbs, etc?

classes defined in db
- orbs required to unlock
- name
- abilities
- prereq classes
- unlocked boolean
- current level and xp

ability cooldowns
when using an ability, add a cooldown row for that ability to the farm

farm needs current class selection
farm has ultimate abilities and unlocked classes
when unlocking class, add to farm's unlocked classes
when using an ability, add to farm's cooldown list

Crafting recipes to combine seeds/orbs/etc to make upgrades/other seeds?
unlock this ability with a class (alchemist?)

seeds have harvestable materials that have to be discovered, show the list with ? if you haven't gotten one yet

seeds can drop:
* more seeds
* seeds from higher plants
* fertilizers
* crafting mats (need to define later)

not predefined: (define probability for these to drop per plant?)
* xp orbs (variable amount)
* unlock orbs (variable amount)

inventory has seeds, consumables (fertilizers, etc that can be used on plots), crafting mats(displayed in farm, only usable in crafting screen)?
unlock orbs are generic, store a quantity

consumables:
* Small Fertilizer
* Large Fertilizer
* Growth Potion


-- classes (5 levels per? keep it simple)

Farm Hand - starter
- 1 harvest seeds? start without the ability to get seeds from harvested plants. make sure to start with enough to xp up to here
- 2 faster grow times
- 3
- 4
- 5 [ult] fertilizer more effective

%%%%%%

Thief - Farm Hand req
- steal more resources from plants?

--- Ninja - Thief req
- harvest double resources, take the goods without the plant even noticing!

------ Pirate - Fighter & Thief req
- grow water plants

%%%%%%

Hunter - Farm Hand req
- bonus to herbs?

---- Ranger - Hunter req


%%%%%%

Hedge Mage - Farm Hand req
- hedge puns! get it?
- bonus to certain types of plants?

---- Alchemist - Hedge Mage req
- make concoctions(fertilizers)
- [ult] free fertilizer buff? with a cooldown

--- Wizard - Hedge Mage req

------ Shaman (Druid?) - hunter and hedge mage?
- naturalist wizard

- Necromancer or Summoner - regrow plants from the dead

%%%%%%

Fighter - Farm Hand req

--- Barbarian - Fighter req
- shouts to make plants grow/produce more, rage at them plants!

--- Monk - Fighter req
- chi powers to grow plants faster?

----- Geomancer - Fighter & Hedge Mage req
- crow crystals!

%%%%%%

===================

GET /api/farm/
    plots and their status, current class, all abilities and cooldowns (ultimates from other classes, plus all from current class)

GET /api/farm/classes/
    get all classes info, unlock status, reqs, serverside calculated 'can be unlocked' flag

GET /api/farm/classes/
    get the info of all classes, with which are unlocked, which can be unlocked(cost and prereqs)

PUT /api/farm/classes/
    change current class

POST /api/farm/classes/
    unlock class

POST /api/farm/plot/
    unlock plot

PUT /api/farm/plot/{plotId}/plant/  requestbody plantType
    plant seed, return plot

PUT /api/farm/plot/{plotId}/harvest/
    harvest plant, return list of rewards. fetch farm to get updated plot and inventory

PUT /api/farm/plot/{plotId}/ability/{abilityId}/
    use ability on plot, create cooldown. fetch farm to get updated plot, inventory and cooldowns

===================
Villager

- Fighter
-- Pugilist
--- Martial Artist
---- Monk
-- Duelist
--- Gladiator
-- Warrior
--- Samuari
-- Knight
--- Paladin
-- Barbarian
--- Berzerker

- Thief
-- Rogue
-- Assassin
--- Ninja
-- Swashbuckler
--- Pirate
-- Bard


- Healer
-- Sage
-- Alchemist    (apothecary/transmuter)
--- Artificer
--- Mage
---- Wizard
--- Sorcerer
-- Cleric
--- Priest
---- Necromancer
---- Summoner
-- Illusionist
--- Mystic
---- Oracle

- Hunter
-- Archer
--- Ranger
-- Scout
-- Shaman
-- Druid
--- Beastmaster
-- Scavenger

enchanter
conjurer

-------------
TODO 
-------------
--* change seeds to db tables instead of enum
--* add progress bars for planted seeds
* add progressive images for plant growth
--* add grow times for seeds
* add default resource lists for seeds
* add extra resources that can drop
--* add better ui to show harvested resources

* define ability tables
* define abilities for a few classes
* show ui for abilities
* drag and drop for using abilities on plots
* click to use abilities that don't target plots

* create fertilizers
--* define inventory of seeds, orbs, fertilizers
--* create ui to show inventory
* drag and drop to plant seeds, add fertilizer, etc

* add images for classes
* add images for plants

--* define level xp tables for classes
--* better ui to show the current class and level progress, on farm and change screen


xp level:
tier 1: 100 xp for max (farm hand)
tier 2: 150 xp for max (classes that require farm hand)
tier 3: 200 xp for max (hybrid classes that depend on tier 2)

Dependencies:
http://bootstrap-notify.remabledesigns.com/#documentation
