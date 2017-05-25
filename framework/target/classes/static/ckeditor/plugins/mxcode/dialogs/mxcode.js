(function () {
    function mxcodeDialog(editor) {
        return {
            title: '二维码/条形码',
            minWidth: 300,
            minHeight: 80,
            buttons: [
            CKEDITOR.dialog.okButton,
            CKEDITOR.dialog.cancelButton],
            contents:
            [
                {
                    id: 'info',
                    label: '二维码/条形码',
                    title: '二维码/条形码',
                    elements:
                    [
                        {
                            id: 'message',
                            type: 'text',
                            style: 'width: 50%;',
                            label: '信息',
                            'default': '',
                            required: true,
                            validate: CKEDITOR.dialog.validate.notEmpty('信息不能为空')
                        }
                        ,
                        {
                            id: 'width',
                            type: 'text',
                            style: 'width: 20%;',
                            label: '宽度',
                            'default': '',
                            required: true,
                            validate: CKEDITOR.dialog.validate.integer('图片宽度应为正整数')
                        }
                        ,
                        {
                            id: 'height',
                            type: 'text',
                            style: 'width: 20%;',
                            label: '高度',
                            'default': '',
                            required: true,
                            validate: CKEDITOR.dialog.validate.notEmpty('图片高度应为正整数')
                        }
                        ,
                        {
                            id: 'image_type',
                            type: 'radio',
                            style: 'width: 20%;',
                            label: '类型',
                            'default': 'BarCode',
                            items: [ [ '条形码', 'BarCode' ], [ '二维码', 'QRCode' ] ],
                        }
                    ]
                }
            ],
//            onLoad: function () {
//                alert('onLoad');
//            },
//            onShow: function () {
//                alert('onShow');
//            },
//            onHide: function () {
//                alert('onHide');
//            },
	          onOk: function () {
	        	  
	        	  //需要生成图片的种类
	        	  var image_type = this.getValueOf('info','image_type');
	        	  //需要封装的信息
	        	  var message = this.getValueOf('info', 'message');
	        	  var width = this.getValueOf('info', 'width');
	        	  var height = this.getValueOf('info', 'height');
	        	  //生成图片的url
	        	  var url = "http://localhost:9090/JFrame/code/"+image_type+"?width="+width+"&height="+height+"&content="+message;
	        	  
	        	  editor.insertHtml("<img alt=\""+message+"\" src=\""+url+"\"width="+width+" height="+height+" />");
	              this.commitContent();
	          },
//            onCancel: function () {
//                alert('onCancel');
//            },
            resizable: CKEDITOR.DIALOG_RESIZE_HEIGHT
        };
    }
    CKEDITOR.dialog.add('mxcode', function (editor) {
        return mxcodeDialog(editor);
    });

})();