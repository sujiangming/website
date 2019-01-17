/*!
 * hezuping
 *描述：金钱转换
 * @TLD.JDRY
 */
//金额值输入
function outputmoney(number) 
{
	number = number.replace(/\,/g, "");
	if (isNaN(number) || number == "")
		return "";
	number = Math.round(number * 100) / 100;
	if (number < 0)
		return '-' + outputChinaYuan(Math.floor(Math.abs(number) - 0) + '')
		+ outputcents(Math.abs(number) - 0);
	else
		return outputChinaYuan(Math.floor(number - 0) + '')
		+ outputcents(number - 0);
}
////格式化金额 --字符串
function outputChinaYuan(number)
{
	if (number.length <= 3)
		return (number == '' ? '0' : number);
	   else {
		var mod = number.length % 3;
		var output = (mod == 0 ? '' : (number.substring(0, mod)));
		for (i = 0; i < Math.floor(number.length / 3); i++)
		{
			if ((mod == 0) && (i == 0))
				output += number.substring(mod + 3 * i, mod + 3 * i + 3);
			else
				output += ','
				+ number.substring(mod + 3 * i, mod + 3 * i + 3);
		}
		
		return (output);
	}
}
function outputcents(amount) 
{
	amount = Math.round(((amount) - Math.floor(amount)) * 100);
	return (amount < 10 ? '.0' + amount : '.' + amount);
}

//金额转换为数值
function tran2Digital(currencmoney)
{
	var money;
	money = currencmoney.replace(/,/g,"");
	return money;
}

