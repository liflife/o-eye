package com.lxf.eye.common.domain;



import java.math.BigDecimal;
import java.util.Date;

public class OrderRandomBuilder {
    public static OrderInfo getNewOrder(){
        OrderInfo order=new OrderInfo();
        String city = createCity();
        order.setAddress(city+createAddress());
        order.setType(OrderTypeEnum.getRandomOne());
        order.setCategory(OrderCategoryEnum.getRandomOne());
        order.setSourceNo(OrderSourceNoEnum.getRandomOne());
        order.setOrderNo(System.currentTimeMillis()+"");
        order.setActualAmount(createActualAmount());
        order.setOriginalAmount(createActualAmount());
        order.setStatus(OrderStatusEnum.WAIT.name());
        order.setReceiver(createReceiver());
        order.setTelephone(createTelephone());
        order.setCity(city);
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        return order;
    }

    private static String familyName="赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董梁杜阮蓝闵席季麻强贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田樊胡凌霍虞万支柯咎管卢莫经房裘缪干解应宗宣丁贲邓郁单杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁荀羊於惠甄魏加封芮羿储靳汲邴糜松井段富巫乌焦巴弓牧隗山谷车侯宓蓬全郗班仰秋仲伊宫宁仇栾暴甘钭厉戎祖武符刘姜詹束龙叶幸司韶郜黎蓟薄印宿白怀蒲台从鄂索咸籍赖卓蔺屠蒙池乔阴郁胥能苍双闻莘党翟谭贡劳逄姬申扶堵冉宰郦雍却璩桑桂濮牛寿通边扈燕冀郏浦尚农温别庄晏柴瞿阎充慕连茹习宦艾鱼容向古易慎戈廖庚终暨居衡步都耿满弘匡国文寇广禄阙东殴殳沃利蔚越夔隆师巩厍聂晁勾敖融冷訾辛阚那简饶空曾毋沙乜养鞠须丰巢关蒯相查后江红游竺权逯盖益桓公万俟司马上官欧阳夏侯诸葛闻人东方赫连皇甫尉迟公羊澹台公冶宗政濮阳淳于仲孙太叔申屠公孙乐正轩辕令狐钟离闾丘长孙慕容鲜于宇文司徒司空亓官司寇仉督子车颛孙端木巫马公西漆雕乐正壤驷公良拓拔夹谷宰父谷粱晋楚阎法汝鄢涂钦段干百里东郭南门呼延归海羊舌微生岳帅缑亢况后有琴梁丘左丘东门西门商牟佘佴伯赏南宫墨哈谯笪年爱阳佟第五言福百家姓续";
    private static String name="克告改攻更杆谷况伽估君吴吸吾圻均坎研完局奇委季宜居届岢岸杰佳京侄佳来例制到兔两典";
    public static String[] address="未来路12号,长江路13号,黄河路13号".split(",");
    public static String[] city="河北,山西,辽宁,吉林,黑龙江,江苏,浙江,安徽,福建,江西,山东,河南,湖北,湖南,广东,海南,四川,贵州,云南, 陕西,甘肃,青海,台湾,北京,天津,上海,重庆,广西,内蒙,西藏,宁夏,新疆".split(",");;
    private static String[] telephone="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");

    private static String createAddress(){
        return address[getRandom(address.length-1)];
    }
    private static String createTelephone(){
        String telephoneIndex=telephone[getRandom(telephone.length-1)];
        return telephoneIndex+getRandom(10000);
    }

   private  static BigDecimal createActualAmount(){

        return new BigDecimal(getRandom(10000));
    }
    private  static String createReceiver(){
        int firstIndex=getRandom(familyName.length()-1);
        String first=familyName.substring(firstIndex, firstIndex+1);

        int lastIndex1=getRandom(name.length()-1);
        int lastIndex2=getRandom(name.length()-1);
        String lastName1=name.substring(lastIndex1, lastIndex1+1);
        String lastName2=name.substring(lastIndex2, lastIndex2+1);
        return first+lastName1+lastName2;
    }
    private  static String createCity(){
        return city[getRandom(city.length-1)].trim();
    }
    private  static int getRandom(int max){
        return (int)(1+Math.random()*max);
    }

}
