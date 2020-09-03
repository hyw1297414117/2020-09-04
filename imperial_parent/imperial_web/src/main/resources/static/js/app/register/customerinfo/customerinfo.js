$(function() {
    
});
/**
 * 保存信息
 * @returns
 */
function submit(){
	if ($.validate.form()) {
        $.operate.save("/customerInfo/add", $('#customerForm').serialize());
    }
}
