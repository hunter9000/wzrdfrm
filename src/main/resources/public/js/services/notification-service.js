
// http://bootstrap-notify.remabledesigns.com/#documentation
wzrdfrmApp.factory('NotificationService', function() {

   var createNotification = function(title, message, template) {
        $.notify({
            // options
            'icon': 'glyphicon glyphicon-warning-sign',
            'title': title,
            'message': message,
//				url: 'https://github.com/mouse0270/bootstrap-notify',
            'target': '_blank'
        },{
            // settings
            element: 'body',
            position: null,
            type: "success",
            allow_dismiss: true,
            newest_on_top: true,
            showProgressbar: false,
            placement: {
                from: "top",
                align: "center"
            },
            offset: 20,
            spacing: 10,
            z_index: 1031,
            delay: 0,
            timer: 0,
            url_target: '_blank',
            mouse_over: null,
            animate: {
                enter: 'animated fadeInDown',
                exit: 'animated fadeOutUp'
            },
            onShow: null,
            onShown: null,
            onClose: null,
            onClosed: null,
            icon_type: 'class',
            'template': template
        });

    }

    return {
        /** Array of HarvestReward to be displayed */
        createHarvestRewardNotification: function(harvestRewards) {
            var message = '';
            harvestRewards.forEach(function(harvestReward) {
                message += '<div class="harvest-reward-wrapper">' + harvestReward.quantity + " " + harvestReward.name + '</div>';
            });

            createNotification(
                    'Harvest Success!',
                    message,
                   `<div ng-controller="harvestRewardNotificationController" data-notify="container" class="col-xs-11 col-sm-3 alert alert-{0}" role="alert">
                        <button type="button" aria-hidden="true" class="close" data-notify="dismiss">×</button>
                        <span class="notification-title" data-notify="title">{1}</span>
                        <span class="notification-message" data-notify="message">{2}</span>
                    </div>`
            );
        },


    };

});