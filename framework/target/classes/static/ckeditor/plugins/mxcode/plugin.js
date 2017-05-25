CKEDITOR.plugins.add('mxcode', {

    init: function (editor) {

        var pluginName = 'mxcode';

        CKEDITOR.dialog.add(pluginName, this.path + 'dialogs/mxcode.js');

        editor.addCommand(pluginName, new CKEDITOR.dialogCommand(pluginName));

        editor.ui.addButton(pluginName,

        {
            label: '二维码/条形码',
            command: pluginName,
            icon: this.path + 'icons/mxcode.png'
        });

    }

});