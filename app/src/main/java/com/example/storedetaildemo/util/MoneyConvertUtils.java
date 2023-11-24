package com.example.storedetaildemo.util;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Locale;

/**
 * 金额工具类
 */
public class MoneyConvertUtils {

    /**
     * 金额为分的格式
     */
    public static final String CURRENCY_FEN_REGEX = "\\-?[0-9]+";

    /**
     * 将分为单位的转换为元并返回金额格式的字符串 （除100）
     *
     * @param amount
     * @return
     * @throws Exception
     */
    public static String changeF2Y(Long amount) throws Exception {
        if (!amount.toString().matches(CURRENCY_FEN_REGEX)) {
            throw new Exception("金额格式有误");
        }

        int flag = 0;
        String amString = amount.toString();
        if (amString.charAt(0) == '-') {
            flag = 1;
            amString = amString.substring(1);
        }
        StringBuffer result = new StringBuffer();
        if (amString.length() == 1) {
            result.append("0.0").append(amString);
        } else if (amString.length() == 2) {
            result.append("0.").append(amString);
        } else {
            String intString = amString.substring(0, amString.length() - 2);
            for (int i = 1; i <= intString.length(); i++) {
                if ((i - 1) % 3 == 0 && i != 1) {
                    result.append(",");
                }
                result.append(intString.substring(intString.length() - i, intString.length() - i + 1));
            }
            result.reverse().append(".").append(amString.substring(amString.length() - 2));
        }
        if (flag == 1) {
            return "-" + result.toString();
        } else {
            return result.toString();
        }
    }

    /**
     * 将分为单位的转换为元 （除100）
     *
     * @param amount
     * @return
     * @throws Exception
     */
    public static String changeF2Y(Context context, String amount) {
        if (!amount.matches(CURRENCY_FEN_REGEX)) {
            Toast.makeText(context, "金额格式有误", Toast.LENGTH_LONG).show();
        }
        return BigDecimal.valueOf(Long.valueOf(amount)).divide(new BigDecimal(100)).toString();
    }

    /**
     * 将元为单位的转换为分 （乘100）
     *
     * @param amount
     * @return
     */
    public static String changeY2F(Long amount) {
        return BigDecimal.valueOf(amount).multiply(new BigDecimal(100)).toString();
    }

    /**
     * 将元为单位的转换为分 替换小数点，支持以逗号区分的金额
     *
     * @param amount
     * @return
     */
    public static String changeY2F(String amount) {
        String currency = amount.replaceAll("\\$|\\￥|\\,", "");  //处理包含, ￥ 或者$的金额
        int index = currency.indexOf(".");
        int length = currency.length();
        Long amLong = 0l;
        if (index == -1) {
            amLong = Long.valueOf(currency + "00");
        } else if (length - index >= 3) {
            amLong = Long.valueOf((currency.substring(0, index + 3)).replace(".", ""));
        } else if (length - index == 2) {
            amLong = Long.valueOf((currency.substring(0, index + 2)).replace(".", "") + 0);
        } else {
            amLong = Long.valueOf((currency.substring(0, index + 1)).replace(".", "") + "00");
        }
        return amLong.toString();
    }

    /**
     * 限制小数点后两位
     *
     * EditText属性改成如下：
     * numberDecimal表示只能输入一个小数点
     *
     *             <EditText
     *                 android:id="@+id/et_amt"
     *                 android:layout_width="wrap_content"
     *                 android:layout_height="wrap_content"
     *                 android:layout_marginLeft="20dp"
     *                 android:background="@null"
     *                 android:layout_weight="1"
     *                 android:layout_gravity="center_vertical"
     *                 android:textColorHint="@color/font_CCCCCC"
     *                 android:textColor="@color/font_333333"
     *                 android:textSize="14sp"
     *                 android:paddingTop="20dp"
     *                 android:paddingBottom="20dp"
     *                 android:inputType="number|numberDecimal"
     *                 android:hint="需小于或等于本期金额"/>
     *
     */
    public static void limitTwoDecimal(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable edt) {
                String temp = edt.toString();
                int posDot = temp.indexOf(".");
                if (posDot <= 0) return;
                if (temp.length() - posDot - 1 > 2) {
                    edt.delete(posDot + 3, posDot + 4);
                }
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }
        });
    }

    /**
     * 汉语中数字大写
     */
    private static final String[] CN_UPPER_NUMBER = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};

    /**
     * 汉语中货币单位大写
     */
    private static final String[] CN_UPPER_MONETRAY_UNIT = {"分", "角", "元", "拾", "佰", "仟", "万", "拾",
            "佰", "仟", "亿", "拾", "佰", "仟", "兆", "拾",
            "佰", "仟"};
    /**
     * 特殊字符：整
     */
    private static final String CN_FULL = "";

    /**
     * 特殊字符：负
     */
    private static final String CN_NEGATIVE = "负";
    /**
     * 零元整
     */
    private static final String CN_ZEOR_FULL = "零元整";

    /**
     * 金额的精度，默认值为2
     */
    private static final int MONEY_PRECISION = 2;

    /**
     * 人民币转换为大写
     *
     * @param number 带转换金额
     * @return x万x千x百x十x元x角x分
     */
    public static String number2CNMoney(String number) {
        return number2CNMoney(new BigDecimal(number));
    }

    /**
     * 获取格式化金额
     *
     * @param money   待处理的金额
     * @param scale   小数点后保留的位数
     * @param divisor 格式化值（10:十元、100:百元,1000千元，10000万元......）
     * @return 123万元，456百万,789亿
     */
    public static String getFormatMoney(BigDecimal money, int scale, double divisor) {
        return formatMoney(money, scale, divisor) + getCellFormat(divisor);
    }

    /**
     * 获取会计格式的人民币
     *
     * @param money   待处理的金额
     * @param scale   小数点后保留的位数
     * @param divisor 格式化值（10:十元、100:百元,1000千元，10000万元......）
     * @return 1, 234, 567.89
     */
    public static String getAccountantMoney(BigDecimal money, int scale, double divisor) {
        return accountantMoney(money, scale, divisor) + getCellFormat(divisor);
    }

    /**
     * 将人民币转换为带货币单位的金额
     *
     * @param money    待转换金额
     * @param locale   区域
     * @param currency 货币型
     * @param scale    小数点后保留的位数
     * @return CNY1999.99
     */
    public static String accountantCurrencyMoneyWithoutComma(BigDecimal money, Locale locale, Currency currency, int scale) {
        String accountantMoney = accountantMoney(money, scale, 1);
        Locale l = locale == null ? Locale.getDefault() : locale;
        Currency c = currency == null ? Currency.getInstance(l) : currency;
        return String.format("%s%s",
                c.getCurrencyCode(),
                accountantMoney.replace(",", ""));
    }

    /**
     * 将人民币转换为带货币单位的金额(当前默认货币型)
     *
     * @param money  待转换金额
     * @param locale 区域
     * @param scale  小数点后保留的位数
     * @return CNY1999.99
     */
    public static String accountantCurrencyMoneyWithoutComma(BigDecimal money, Locale locale, int scale) {
        Locale l = locale == null ? Locale.getDefault() : locale;
        return accountantCurrencyMoneyWithoutComma(money, locale, Currency.getInstance(l), scale);
    }

    /**
     * 将人民币转换为带货币单位的金额(当前默认区域)
     *
     * @param money 待转换金额
     * @param scale 小数点后保留的位数
     * @return CNY1999.99
     */
    public static String accountantCurrencyMoneyWithoutComma(BigDecimal money, int scale) {
        return accountantCurrencyMoneyWithoutComma(money, null, scale);
    }

    /**
     * 将人民币转换为带货币单位的金额(保留两位小数)
     *
     * @param money 待转换金额
     * @return CNY1999.99
     */
    public static String accountantCurrencyMoneyWithoutComma(BigDecimal money) {
        return accountantCurrencyMoneyWithoutComma(money, 2);
    }

    /**
     * 将人民币转换为会计格式金额(保留两位小数)
     *
     * @param money 待转换的金额
     * @return 1, 234, 567.89
     */
    public static String accountantMoney(BigDecimal money) {
        return accountantMoney(money, 2, 1);
    }

    /**
     * 人民币转换为大写
     *
     * @param number 带转换金额
     * @return x万x千x百x十x元x角x分
     */
    public static String number2CNMoney(BigDecimal number) {
        StringBuffer sb = new StringBuffer();
        int signum = number.signum();
        // 零元整的情况
        if (signum == 0) {
            return CN_ZEOR_FULL;
        }
        //这里会进行金额的四舍五入
        long numberScale = number.movePointRight(MONEY_PRECISION).setScale(0, 4).abs().longValue();
        // 得到小数点后两位值
        long scale = numberScale % 100;
        int numUnit = 0;
        int numIndex = 0;
        boolean getZero = false;
        // 判断最后两位数，一共有四中情况：00 = 0, 01 = 1, 10, 11
        if (scale <= 0) {
            numIndex = 2;
            numberScale = numberScale / 100;
            getZero = true;
        }
        if ((scale > 0) && (scale % 10 <= 0)) {
            numIndex = 1;
            numberScale = numberScale / 10;
            getZero = true;
        }
        int zeroSize = 0;
        while (true) {
            if (numberScale <= 0) {
                break;
            }
            // 每次获取到最后一个数
            numUnit = (int) (numberScale % 10);
            if (numUnit > 0) {
                if ((numIndex == 9) && (zeroSize >= 3)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[6]);
                }
                if ((numIndex == 13) && (zeroSize >= 3)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[10]);
                }
                sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                getZero = false;
                zeroSize = 0;
            } else {
                ++zeroSize;
                if (!(getZero)) {
                    sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                }
                if (numIndex == 2) {
                    if (numberScale > 0) {
                        sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                    }
                } else if (((numIndex - 2) % 4 == 0) && (numberScale % 1000 > 0)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                }
                getZero = true;
            }
            // 让number每次都去掉最后一个数
            numberScale = numberScale / 10;
            ++numIndex;
        }
        // 如果signum == -1，则说明输入的数字为负数，就在最前面追加特殊字符：负
        if (signum == -1) {
            sb.insert(0, CN_NEGATIVE);
        }
        // 输入的数字小数点后两位为"00"的情况，则要在最后追加特殊字符：整
        if (scale <= 0) {
            sb.append(CN_FULL);
        }
        return sb.toString();
    }

    /**
     * 将人民币转换为会计格式金额
     *
     * @param money   待处理的金额
     * @param scale   小数点后保留的位数
     * @param divisor 格式化值（10:十元、100:百元,1000千元，10000万元......）
     * @return 1, 234, 567.89
     */
    private static String accountantMoney(BigDecimal money, int scale, double divisor) {
        String disposeMoneyStr = formatMoney(money, scale, divisor);
        //小数点处理
        int dotPosition = disposeMoneyStr.indexOf(".");
        String exceptDotMoeny = null;//小数点之前的字符串
        String dotMeony = null;//小数点之后的字符串
        if (dotPosition > 0) {
            exceptDotMoeny = disposeMoneyStr.substring(0, dotPosition);
            dotMeony = disposeMoneyStr.substring(dotPosition);
        } else {
            exceptDotMoeny = disposeMoneyStr;
        }
        //负数处理
        int negativePosition = exceptDotMoeny.indexOf("-");
        if (negativePosition == 0) {
            exceptDotMoeny = exceptDotMoeny.substring(1);
        }
        StringBuffer reverseExceptDotMoney = new StringBuffer(exceptDotMoeny);
        reverseExceptDotMoney.reverse();//字符串倒转
        char[] moneyChar = reverseExceptDotMoney.toString().toCharArray();
        StringBuffer returnMeony = new StringBuffer();//返回值
        for (int i = 0; i < moneyChar.length; i++) {
            if (i != 0 && i % 3 == 0) {
                returnMeony.append(",");//每隔3位加','
            }
            returnMeony.append(moneyChar[i]);
        }
        returnMeony.reverse();//字符串倒转
        if (dotPosition > 0) {
            returnMeony.append(dotMeony);
        }
        if (negativePosition == 0) {
            return "-" + returnMeony.toString();
        } else {
            return returnMeony.toString();
        }
    }

    /**
     * 格式化金额
     *
     * @param money   待处理的金额
     * @param scale   小数点后保留的位数
     * @param divisor 格式化值
     * @return xxx万元，xxx百万,xxx亿
     */
    private static String formatMoney(BigDecimal money, int scale, double divisor) {
        if (divisor == 0) {
            return "0.00";
        }
        if (scale < 0) {
            return "0.00";
        }
        BigDecimal divisorBD = BigDecimal.valueOf(divisor);
        return money.divide(divisorBD, scale, RoundingMode.HALF_UP).toString();
    }

    private static String getCellFormat(double divisor) {
        String str = String.valueOf(divisor);
        int len = str.substring(0, str.indexOf(".")).length();
        String cell = "";
        switch (len) {
            case 1:
                cell = "元";
                break;
            case 2:
                cell = "十元";
                break;
            case 3:
                cell = "百元";
                break;
            case 4:
                cell = "千元";
                break;
            case 5:
                cell = "万元";
                break;
            case 6:
                cell = "十万元";
                break;
            case 7:
                cell = "百万元";
                break;
            case 8:
                cell = "千万元";
                break;
            case 9:
                cell = "亿元";
                break;
            case 10:
                cell = "十亿元";
                break;
            default:
                cell = "元";
                break;
        }
        return cell;
    }
}
