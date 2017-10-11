package thinking.in.java.Eexception.Exception;

/**
 * 动态的向DynamicFields对象中添加字段
 * 注意：所有的Throwable子类中都可以接受一个cause（因由）对象作为参数，用来表示原始对象的异常信息
 * 也能通过这个异常追踪到异常最初发生的位置；有趣的是，只有三种基本的异常提供了带有cause参数的构造器
 * Error（用于java虚拟机报告系统错误）、Exception、RuntimeException
 * 要把其他异常链接起来，应该使用initCause()方法而不是构造器
 */

class DynamicFieldsException extends Exception{}

public class DynamicFields {
    private Object[][] fields;//Object数组中的数组
    //初始化数组的数组（数组中的数组只有两个值 key - value 初始值为null）
    public DynamicFields(int initialSize){
        fields = new Object[initialSize][2];
        for(int i=0; i<initialSize; i++){
            fields[i] = new Object[]{ null, null };
        }
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        //将数组的值转换成字符串
        for(Object[] obj : fields){
            result.append(obj[0]);
            result.append(": ");
            result.append(obj[1]);
            result.append("\n");
        }
        return result.toString();
    }

    //判断数组中是否存在值，存在返回其所在的下标，不存在返回负一（key）
    private int hasField(String id){
        for(int i=0; i< fields.length; i++){
            if(id.equals(fields[i][0])){
                return i;
            }
        }
        return -1;
    }

    //获取字段下标
    private int getFieldNumber(String id) throws NoSuchFieldException{
        int fieldNum = hasField(id);
        if(fieldNum == -1){
            throw new NoSuchFieldException();
        }
        return fieldNum;
    }

    //创建新的字段
    private int makeField(String id){
        //将初始化为空的值赋值（key）
        for(int i = 0; i<fields.length;i++){
            if(fields[i][0] == null){
                fields[i][0] = id;
                return i;
            }
        }

        //先创建一个数组长度比原始的数组长度大一（数组是2则为 0，1 两个下标）
        Object[][] tmp = new Object[fields.length + 1][2];
        //将原始的数组的长度复制给新数组
        for(int i=0; i<fields.length; i++){
            tmp[i] = fields[i];
        }
        //将新数组的最后一个长度复制一个对象
        for(int i=fields.length; i<tmp.length; i++){
            tmp[i] = new Object[]{ null, null };
        }
        fields = tmp;//进行数组替换
        return makeField(id);//在次调用将初始化为空的值赋值
    }

    //得到字段的值
    public Object getField(String id) throws NoSuchFieldException{
        return fields[getFieldNumber(id)][1];
    }

    //设置字段并创建新的字段
    public Object setField(String id, Object value) throws DynamicFieldsException{
        // 值为空则抛出异常
        if(value == null){
            //注意：所有的Throwable子类中都可以接受一个cause（因由）对象作为参数，用来表示原始对象的异常信息
            DynamicFieldsException dfe = new DynamicFieldsException();
            dfe.initCause(new NullPointerException());
            throw dfe;
        }
        int fieldNumber = hasField(id);
        if(fieldNumber == -1){
            fieldNumber = makeField(id);
        }
        Object result = null;
        try {
            result = getField(id);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        fields[fieldNumber][1] = value;
        return result;
    }

    public static void main(String[] args){
        DynamicFields df = new DynamicFields(3);
        System.out.println(df);
        try {
            df.setField("d","A value for d");
            df.setField("number",47);
            df.setField("number2",48);
            System.out.println(df);
            df.setField("d","A new value for d");
            df.setField("number3", "11");
            System.out.println(df);
            System.out.println("df.getField(\"d\") " + df.getField("d"));
//            Object field = df.setField("d", null);
        } catch (DynamicFieldsException e) {
            e.printStackTrace(System.out);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }






}




















