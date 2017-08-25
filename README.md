
crafting

Get recipes,inventory from char
Api/char/1
 
Show recipe selection and inventory
Choose recipe to see stats from 
api/char/recipe/Id
Show recipe item, base mats, inventory minus base. Inv items only have mat effects relevant to base item type. 
Remaining capacity
Add mats from inv Ctrl click
 
Show collection of mat effects, summed up by quantity under each selection
Grey out inv items that are over remaining capacity
 
Post selected mat Id and quantity and recipe to 
Api/char/recipe/id


Predefined list of materials and what effects they add
- Material
-- MaterialEffects

Recipe that defines the item that it makes, and what materials are required for base
- Recipe
-- Item
-- RecipeRequirement
--- Material

A crafted, equippable item, and the material effects that it was crafted with
- Equipment
-- Item
-- ItemEffect
--- MaterialEffect

A material in a character's inventory
- Inventory Material
-- Material
-- quantity

Base definition of an item
- Item


materials
- woods:
-- softwoods
cedar
pine
cypress
fir
yew
hemlock
spruce
-- hardwood
alder
chestnut
ash
aspen
birch
blackwood
walnut
cherry
elm
eucalyptus
hickory
ironwood
kingwood
locust
mahogany
maple
oak
poplar
sandalwood
teak
bamboo


- metals:
iron
tin
bronze
copper
lead
gold
silver
aluminum
chromium
magnesium
nickel
titanium
zinc
quiksilver
brass

- food
salt
meats
marrow
roots
nuts
fruits

- papers/cloths
wool
fur
silk
grass
hemp
sisal
straw
cotton
flax
jute
rawhide
leather
burlap
canvas
twill
fleece
Gingham



- crystals/gems
ice
diamond
amythest
aquamarine
beryl
emerald
citrine
ruby
sapphire
feldspar
moonstone
sunstone
garnet
topaz
hematite
jasper
lapis lazuli
opal
quartz
flint
agate
tiger's eye
tourmaline
turquoise
spinel
jet
coral
pearl
obsidian

amber
bone
tar
gunpowder stuff
saltpeter
sulfur
clay
charcoal
phosphorus


waters:
aqua vitae
etc


mandrake
mushrooms
molds and lichens
wolfsbane
salts
pyrite

vinegar
limestone


fangs/teeth
feathers
blood
eyes
spores
tendons
venom
heart


-- classes

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



