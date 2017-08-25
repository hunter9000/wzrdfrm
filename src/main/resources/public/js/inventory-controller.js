
avalonApp.controller('inventoryController', function(APIService, InventoryService, ImageService, ItemService, $scope, $routeParams) {

    $scope.char;

    $scope.equipmentFilterOptions = ItemService.getEquipmentSlots().map(function(currentValue, index, array) {
        return {'label': currentValue, 'image': ImageService.getEquipmentSlotIcon(currentValue), 'filter': currentValue}
    });

    $scope.equipmentFilter = [];

    $scope.filterEquipmentFunction = function(hash) {
        return ($scope.equipmentFilter.indexOf(hash.item.bodySlot) !== -1);
    };

//    CONSUMABLE,
//    HEAD,
//    BODY,
//    LEGS,
//    FEET,
//    HANDS,
//    RING,
//    NECK,
//    LEFT_HAND,
//    RIGHT_HAND

// pants
//game-icons.net.png\icons\irongamer\originals\png\000000\transparent\armored-pants.png
// hand
//game-icons.net.png\icons\john-redman\hands\png\000000\transparent\paper.png
// chest
//game-icons.net.png\icons\lorc\originals\png\000000\transparent\armor-vest.png
// head
//game-icons.net.png\icons\lorc\originals\png\000000\transparent\barbute.png
// bare foot
//game-icons.net.png\icons\lorc\originals\png\000000\transparent\barefoot.png
// boots
//game-icons.net.png\icons\lorc\originals\png\000000\transparent\boots.png
// chest
//game-icons.net.png\icons\lorc\originals\png\000000\transparent\breastplate.png
// ring
//game-icons.net.png\icons\lorc\originals\png\000000\transparent\engagement-ring.png
// necklace
//game-icons.net.png\icons\lorc\originals\png\000000\transparent\gem-chain.png

//anvil
//game-icons.net.png\icons\lorc\originals\png\000000\transparent\anvil.png
// chopping wood
//game-icons.net.png\icons\lorc\originals\png\000000\transparent\axe-in-stump.png
// equipment
//game-icons.net.png\icons\lorc\originals\png\000000\transparent\battle-gear.png
// flask
//game-icons.net.png\icons\lorc\originals\png\000000\transparent\bubbling-flask.png
// cauldron
//game-icons.net.png\icons\lorc\originals\png\000000\transparent\cauldron.png
// mining diamond
//game-icons.net.png\icons\lorc\originals\png\000000\transparent\diamond-hard.png
// footprint
//game-icons.net.png\icons\lorc\originals\png\000000\transparent\footprint.png

// all willdabeast\deviations are good

// \viscious-speed\abstract for abstract shapes

    APIService.getChar($routeParams.charId, function(response) {
        $scope.char = response.data;
    });

    $scope.inventoryMaterialSelect = function(invMaterial, event) {
        console.log(invMaterial);
    }

    $scope.inventoryEquipmentSelect = function(invEquipment, event) {
        console.log(invEquipment);
        // show difference between this and equipped item
    }

    $scope.equipItem = function(invEquipment, event) {
        console.log('equiping item');
        console.log(invEquipment);

        var equipRequest = InventoryService.getEquipmentRequest(invEquipment.id, invEquipment.item.bodySlot);

        APIService.equipItem($routeParams.charId, equipRequest, function(response) {
            $scope.char = response.data;
        })
    }


    $scope.getItemIcon = function(invEquipment) {
        // just return the body slot icon for now
        return ImageService.getEquipmentSlotIcon(invEquipment.item.bodySlot);
    }
});