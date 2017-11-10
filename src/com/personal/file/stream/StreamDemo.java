package com.personal.file.stream;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 构造流的几种常见方法
 * 有多种方式生成 Stream Source：
 * Collection :
 * 1.Collection.stream()
 * 2.Collection.parallelStream()
 * 数组 ：
 * 1.Arrays.stream(T array)
 * 2.Stream.of()
 * IO流 ：
 * 1.java.util.stream.IntStream.range()
 * 2.java.nio.file.Files.walk()
 * 自己构建 ：
 * 1.java.util.Spliterator
 * 其他 :
 * 1.Random.ints()
 * 2.BitSet.stream()
 * 3.Pattern.splitAsStream(java.lang.CharSequence)
 * 4.JarFile.stream()
 */
public class StreamDemo {

    //Individual values
    private static void StreamValues(){
        Stream.of("a","b","c").forEach(System.out::print);
    }

    //Arrays
    private static void StreamArrays(){
        //1
        String[] strArray = new String[]{"a", "b", "c"};
        Stream stream = Stream.of(strArray);
        Optional optional  = stream.filter(v -> "a".equals(v)).map(v -> v.toString()).findFirst();
        System.out.println();
        System.out.println(optional.get());
        //2
        Arrays.stream(strArray).forEach(System.out::print);
    }

    //Collections
    private static void StreamCollections(){
        String[] strArray = new String[]{"a", "b", "c"};
        List<String> list = Arrays.asList(strArray);
        System.out.println();
        list.stream().forEach(System.out::print);
    }

    /**
     * 对于基本的数值，目前有三种对应的包装类型 Stream :
     * IntStream、LongStream、DoubleStream
     * 当然我们也可以用：
     * String<Integer>、String<Long>、String<Double>
     * 但是boxing和unboxing会很耗时，所以特别为这三种基本数值类型提供了对应的Stream
     */
    private static void IntStreamDemo(){
        IntStream.of(new int[]{1, 2, 3}).forEach(System.out::print);
        System.out.println();
        IntStream.range(1,4).forEach(System.out::print);//半包围结构，含头不含尾
        System.out.println();
        IntStream.rangeClosed(1,3).forEach(System.out::print);//全包围结构
    }

    /**
     * 流的数据结构转换
     * 1.Array
     */
    private static void StreamConvertArray(){
        //1.Array
        Stream stream = Stream.of("a","b","c");
        Object[] object = stream.toArray(String[]::new);
        if(object instanceof String[]){
            String[] str = (String[]) object;
            for(String s : str){
                System.out.print(s);
            }
        }
    }

    /**
     * 流的数据结构转换
     * 2.Collection
     */
    private static void StreamConvertCollection(){
        //1.list集合
        Stream stream = Stream.of("a","b","c");
        Object list1 = stream.collect(Collectors.toList());
        System.out.println(list1);
        //2.list集合
        Stream stream1 = Stream.of("li","wen","ya");
        Object list2 = stream1.collect(Collectors.toCollection(ArrayList::new));
        List<String> list = null;
        if (list2 instanceof List){
            list = (List<String>) list2;
        }
        System.out.println(list);
        //3.set集合
        String[] str = new String[]{"he","hui"};
        Stream stream2 = Arrays.stream(str);
        Object set = stream2.collect(Collectors.toSet());
        System.out.println(set);
    }

//    public static void main(String[] args) {
//        StreamConvertCollection();
//    }

    /**
     * 流的数据结构转换
     * 3.String
     */
    private static void StreamConvertString(){
        Stream stream = Stream.of("a","b","c");
        String string = stream.collect(Collectors.joining()).toString();
        System.out.println(string);
    }

//    public static void main(String[] args) {
//        StreamConvertString();
//    }

    /**
     * 流的操作：
     * 当把一个数据结构包装成Stream后，就要开始对里面的元素进行各类操作了，
     * 常见的操作可以归类如下
     *
     * 1.Intermediate（一个流可以有多个Intermediate方法）
     * map(mapToInt,flatMap等)、filter、sorted、distinct、peek、limit、skip、parallel、sequential、unordered
     *
     * 2.Terminal（一个流只能有一个Terminal方法）
     * forEach、forEachOrdered、toArray、reduce、collect、min、max、count、anyMatch、allMatch、
     * noneMatch、findFirst、findAny、iterator
     *
     * 3.Short-circuiting（短路：在无线的流里找到有限的数据）
     * anyMatch、allMatch、noneMatch、findFirst、findAny、limit
     */
    public static void methods(){}

    /**
     * Stream比较经典的用法
     *
     * map/flatMap
     * 先看map，如果熟悉scala这类函数式语言，就明白它的作用：
     * 把input Stream的每一个元素，映射成output Stream的另外一个元素
     */
    private static void mapConvertUpperCase(){
        String[] wordList = new String[]{"li","wen","ya"};
        List<String> output = Arrays.stream(wordList)
                .map(String::toUpperCase).collect(Collectors.toCollection(ArrayList::new));
        System.out.println(output);
    }
    private static void mapConvertSquare(){
        List<Integer> nums = Arrays.asList(1,2,3,4);
        List<Integer> squareNums = nums.stream().map(n -> n * n).collect(Collectors.toList());
        System.out.println(squareNums);
    }

//    public static void main(String[] args) {
////        mapConvertUpperCase();
//        mapConvertSquare();
//    }

    /**
     * 从上面例子可以看出，map生成的是个1:1映射，每个输入元素，都按照规则转换成为另外一个元素，
     * 还有一些场景，是一对多映射关系的，这时需要flatMap;
     *
     * flatMap 把 input Stream 中的层级结构扁平化，就是将最底层元素抽出来放到一起，
     * 最终 output 的新 Stream 里面已经没有 List 了，都是直接的数字。
     */
    private static void flatMapConvert(){
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2,3),
                Arrays.asList(4,5,6)
        );
        Stream stream = inputStream.flatMap((childList) -> childList.stream());
        Object list = stream.collect(Collectors.toCollection(ArrayList::new));
        System.out.println(list);

        Stream<List<Integer>> inputStream1 = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2,3),
                Arrays.asList(4,5,6)
        );
        inputStream1.forEach(System.out::println);
    }

//    public static void main(String[] args) {
//        flatMapConvert();
//    }

    /**
     * filter :
     * filter 对原始 Stream 进行某项测试，通过测试的元素被留下来生成一个新Stream
     */
    private static void filterDemo(){
        Integer[] sixNums = {1, 2, 3, 4, 5, 6};
        Integer[] evens = Stream.of(sixNums).filter(n -> n%2 == 0).toArray(Integer[]::new);
        for(Integer in : evens){
            System.out.print(in+" ");
        }
    }

//    public static void main(String[] args) {
//        filterDemo();
//    }

    /**
     * forEach : terminal操作（只能有一个）执行完操作就将流消费掉了
     *
     * forEach方法接受一个Lambda表达式，然后再Stream的每个元素上执行该表达式
     * roster.stream()
     * .filter(p -> p.getGender() == Person.Sex.MALE)
     * .forEach(p -> System.out.println(p.getName()));
     *  // Pre-Java 8
     * for (Person p : roster) {
     *  if (p.getGender() == Person.Sex.MALE) {
     *  System.out.println(p.getName());
     * }
     * }
     * 上例子可以看出：
     * forEach 是为 Lambda 而设计的，保持了最紧凑的风格。而且 Lambda 表达式本身是可以重用的，
     * 非常方便。当需要为多核系统优化时，可以 parallelStream().forEach()，
     * 只是此时原有元素的次序没法保证，并行的情况下将改变串行时操作的行为，
     * 此时 forEach 本身的实现不需要调整，
     * 而 Java8 以前的 for 循环 code 可能需要加入额外的多线程逻辑。
     *
     * 但一般认为，forEach 和常规 for 循环的差异不涉及到性能，
     * 它们仅仅是函数式风格与传统 Java 风格的差别
     *
     * 另外一点需要注意，forEach 是 terminal 操作，因此它执行后，
     * Stream 的元素就被“消费”掉了，你无法对一个 Stream 进行两次 terminal 运算。
     *
     * 相反：具有相似功能的 intermediate 操作 peek 可以达到上述目的。
     *
     * forEach不能修改自己包含的本地变量值，也不能用break/return之类的关键字提前结束循环
     */
    private static void forEachDemo(){
        //1
        String[] str = "new void thread fixed executors pool cached".split(" ");
        Stream.of(str).filter(p -> "pool".equals(p)).forEach(p -> System.out.println(p));
        //2
        String[] string = "new void thread fixed executors pool cached".split(" ");
        Stream.of(string).filter(p -> "new".equals(p)).forEach(System.out::println);
    }

    /**
     * peek ：intermediate操作（可有多个）
     * peek：此操作具有与forEach（terminal）操作功能相同
     * peek对每个元素执行操作并返回一个新的Stream
     */
    private static void peekDemo(){
        List<String> list = Stream.of("one","two","three","four").filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
        System.out.println(list);
    }

    /**
     * findFirst
     * 这是一个terminal兼short-circuiting操作，他总是返回Stream的第一个元素或者空
     *
     * 重点是它返回值得类型：Optional。这也是模仿Scala语言中的概念，作为一个容器，
     * 它可能含有某值，或者不包含。使用它的目的是尽可能避免NullPointerException
     *
     * Stream中的findAny、max/min、reduce等等 方法返回Optional值。还有例如
     * IntStream.average() 返回OptionalDouble等等
     */
    private static void findFirstDemo(){}

    /**
     * Optional的用例
     */
    private static void optionalDemo(){
        String strA = " abcd ", strB = null;
        print(strA);
        print("");
        print(strB);
        getLength(strA);
        getLength("");
        getLength(strB);
    }
    private static void print(String text){
        //Java 8
        Optional.ofNullable(text).ifPresent(System.out::println);
//        if(text != null){
//            System.out.println(text);
//        }
    }
    private static int getLength(String text){
        //Java 8
        return Optional.ofNullable(text).map(String::length).orElse(-1);
        //Java 8
        //return if(text != null) ? text.length() : -1;

    }

    /**
     * reduce
     * 这个方法的主要作用是把Stream元素组合起来。它提供一个起始（种子），然后依照运算规则（BinaryOperator），
     * 和前面Stream的第一个，第二个，第n个元素组合；从这个意义上说，字符串拼接、数值的sum
     * min、max、average都是特殊的reduce。例如Stream的sum就相当于
     * Integer sum = integers.reduce(0, (a, b) -> a+b); 或
     * Integer sum = integers.reduce(0, Integer::sum);
     * 也有没有起始值得情况，这时会把Stream的前面两个元素组合起来，返回的是Optional
     *
     * 例如下面的代码：
     * 第一个实例的reduce()，第一个参数（空白字符）即为起始值，第二个参数（String::concat）
     * 为BinaryOperator。这类有起始值得reduce()都返回具体的对象。而对于第四个实例没有起始
     * 值得reduce(),由于可能没有足够的元素，返回的是Optional，请留意这个区别
     *
     */
    private static void reduceDemo(){
        // 字符串链接，concat = “ABCD”
        String concat = Stream.of("A","B","C","D").reduce("", String::concat);
        System.out.println(concat);
        //求最小值，minValue = -3.0
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        System.out.println(minValue);
        //求和， sumValue = 10, 有起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        System.out.println(sumValue);
        //求和， sumValue = 10, 无起始值
        sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        System.out.println(sumValue);
        //过滤，字符串连接，concat = “ace”
        concat = Stream.of("a", "B", "c", "D", "e", "F")
                .filter(x -> x.compareTo("Z") > 0).reduce("", String::concat);
        System.out.println(concat);
    }

    /**
     * limit/skip
     * limit返回Stream的前面n个元素；skip则是扔掉前n个元素
     * （它是有一个叫subStream的方法改名而来）
     *
     * limit和skip对运行次数的影响;
     *
     * 下面的例子是有10000个元素的Stream，但在short-circuiting操作limit和
     * skip的作用下，管道中map操作制定的getName()方法的执行次数为limit所限定的
     * 10次，而最终返回结果在跳过钱3个元素后只有后面7个返回。
     */
    private static void testLimitAndSkip(){
        List<Person> persons = new ArrayList<>();
        for(int i = 1; i<=10000; i++){
            Person person = new Person(i, "name"+i);
            persons.add(person);
        }
        List<String> strings1 = persons.stream().map(Person::getName)
                .limit(10).skip(3).collect(Collectors.toList());
        System.out.println(strings1);
    }
    private static class Person{
        public int no;
        private String name;
        private Integer age;
        public Person(int no, String name){
            this.no = no;
            this.name = name;
        }
        public Person(int no, String name, Integer age){
            this.no = no;
            this.name = name;
            this.age = age;
        }
        public String getName(){
//            System.out.println(name);
            return name;
        }
        public Integer getAge() {
            return age;
        }
    }

    /**
     * 有一种情况是limit/skip无法达到short-circuiting目的地，就是把他们放在Stream的排序操作后，
     * 原因跟sorted这个intermediate操作有关；此时系统并不知道Stream排序后的次数如何，所以sorted
     * 中的操作看上去就像完全没有被limit或者skip一样
     *
     * limit和skip对sorted后的运行次数无影响 :
     *
     * 即虽然最后的返回元素数量是 2，但整个管道中的 sorted 表达式执行次数没有像前面例子相应减少。
     *
     * 最后有一点需要注意的是，对一个parallel的Steam管道来说，如果其元素是有序的，那么limit操作侧成本
     * 会比较大，因为它的返回对象必须是前n个也有一样次序的元素。取而代之的策略是取消元素间的次序，或者
     * 不要用parallelStream
     *
     */
    private static void test1LimitAndSkip(){
        List<Person> persons = new ArrayList<>();
        for(int i = 1; i<=5; i++){
            Person person = new Person(i,"name"+i);
            persons.add(person);
        }
        List<Person> personList = persons.stream()
                .sorted((p1,p2) -> p1.getName().compareTo(p2.getName()))
                .limit(2).collect(Collectors.toList());
        System.out.println(personList);
    }

    /**
     * sorted
     * 对Stream的排序通过sorted进行，它比数组的排序更强之处在于你可以首先对Stream
     * 进行各类map、filter、limit、skip甚至distinct来减少元素数量后，在排序，这能
     * 帮助程序明显缩短执行时间。
     *
     * 当然，这种优化是有business logic上的局限性的：即不要求排序后在取值。
     */
    private static void sortedDemo(){
        List<Person> persons = new ArrayList<>();
        for(int i=1; i<=5; i++){
            Person person = new Person(i, "name"+i);
            persons.add(person);
        }
        List<Person> persons1 = persons.stream()
                .limit(2).sorted((p1,p2) -> p1.getName().compareTo(p2.getName()))
                .collect(Collectors.toList());
        System.out.println(persons1.toString());
    }
    private static void sortedDemo1(){
        List<Person> persons = new ArrayList<>();
        for(int i=1; i<=5;i++){
            Person person = new Person(i, "name"+i);
            persons.add(person);
        }
        List<Person> persons1 = persons.stream().limit(2)
                .sorted(Comparator.comparing(Person::getName).reversed())
                .collect(Collectors.toList());
        System.out.println(persons1);
    }

    /**
     * min/max/distinct ：
     * min和max的功能可以通过Stream元素先排序，在findFirst来实现，但前者的性能会更好，
     * 为O(n)，而sorted的成本是O(n log n)。同时他们作为特殊的reduce方法被独立出来也是
     * 因为求最大最小值是很常见的操作
     */
    private static void maxDemo(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("e:\\SUService.txt"));
            int longest = br.lines().mapToInt(String::length).max().getAsInt();
            br.close();
            System.out.println(longest);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * max/min/distinct ：
     * 下面的例子 则使用distinct来找出不重复的单词
     * 找出全文单词，转大写，并排序
     */
    private static void distinctDemo(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("e:\\SUService.txt"));
            List<String> words = br.lines()
                    .flatMap(line -> Stream.of(line.split(" ")))
                    .filter(world -> world.length() > 3)
                    .map(String::toUpperCase)
                    .distinct()//独立（去重功能）
                    .sorted()
                    .collect(Collectors.toList());
            br.close();
            System.out.println(words);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Match :
     * Stream 有三个match方法，从语义上说：
     * 1.allMatch : Stream 中全部元素符合传入的 predicate(断言)，返回true
     * 2.anyMatch : Stream 中只要有一个元素符合传入的 predicate，返回true
     * 3.noneMatch : Stream 中没有一个元素符合传入的 predicate，返回true
     *
     * 它们都不是要便利全部元素才能返回结果。例如allMatch只要一个元素不满足条件，
     * 就skip（subStream）剩下的所有元素，返回false。
     *
     */
    private static void matchDemo(){
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1,"name"+1,10));
        persons.add(new Person(2,"name"+2,21));
        persons.add(new Person(3,"name"+3,34));
        persons.add(new Person(4,"name"+4,6));
        persons.add(new Person(5,"name"+5,55));
        boolean isAllAdult = persons.stream().allMatch(p -> p.getAge() > 18);
        System.out.println("All ar adult? "+isAllAdult);
        boolean isThereAnyChild = persons.stream().anyMatch(p -> p.getAge() < 12);
        System.out.println("Any child? "+isThereAnyChild);
    }

    /**
     * 进阶：自己生成的流
     * Stream.generate
     * 通过实现Supplier接口你可以自己来控制流的生成。这种情形通常用于随机数、常量的Stream
     * 或者需要前后元素间维持着某种状态信息的Stream。把Supplier实例传递给Stream.generator()
     * 生成的Stream，默认是串行（相对parallel而言）但无序的（相对ordered而言）。由于它是无限的，
     * 在管道中，必须利用limit之类的操作限制Stream大小
     *
     * Stream.generate()还接受自己实现的Supplier。例如在构造海量的是数据的时候，用某种自动的规则
     * 给每个变量赋值；或者依据公式计算Stream的每个元素值。这些都是维持状态信息的情形。
     */
    private static void streamGenerateDemo(){
        Random seed = new Random();
        Supplier<Integer> random = seed::nextInt;
        Stream.generate(random).limit(2).forEach(System.out::println);
        //Another way
        IntStream.generate(() -> (int)(System.nanoTime()%100)).limit(10).forEach(n -> System.out.print(n + " "));
    }

    /**
     * 自实现Supplier
     */
    private static void supplierDemo(){
        Stream.generate(new PersonSupplier()).limit(10)
                .sorted((p1,p2) -> p1.getAge().compareTo(p2.getAge()))
                .forEach(p -> System.out.println(p.getName()+", "+p.getAge()));
    }
    private static class PersonSupplier implements Supplier<Person>{
        private int index = 0;
        private Random random = new Random();
        @Override
        public Person get() {
            return new Person(index++, "StormTestUser"+index, random.nextInt(100));
        }
    }

    /**
     * Stream.iterate :
     * iterate跟reduce操作很像，接受一个种子值，和一个UnaryOperator（例如f）。
     * 然后种子值成为Stream的第一个元素,f(seed)为第二个，f(f(seed))第三个，以此类推
     *
     * 与Stream.generate相仿，在iterate时候管道必须有limit这样的操作来限制Stream大小
     */
    private static void streamIterateDemo(){
        Stream.iterate(0, n -> n + 3).limit(10).forEach(x -> System.out.print(x + " "));
    }

    /**
     * 进阶：用 Collectors 来进行 reduction(减少) 操作
     *
     * java.util.stream.Collectors类的主要作用就是辅助进行各类有用的reduction操作，
     * 例如转变输出为Collection，把Stream元素进行归组。
     * groupingBy（被分组：相同的为一组）/partitioningBy（被分割：符合条件的一组，不符合条件的一组）
     *
     * partitioningBy 其实是一种特殊的 groupingBy，它依照条件测试的是否两种结果来构造返回的
     * 数据结构，get(true)和get(false)即为全部的元素对象
     *
     * groupingBy/partitioningBy 两者返回的都是Map<K,List<T>> 其中K代表经过条件返回的数值类型
     * List<T>保存的是对象的数量
     *
     * 例：按照年龄归组(年龄相同的为一组)
     */
    private static void groupingByDemo(){
        //Map<Integer, List<Person>> 其中Integer保存的是分组的条件，List<Person> 保存的是数量
        Map<Integer, List<Person>> personGroups = Stream.generate(new PersonSupplier())
                .limit(100)
                .collect(Collectors.groupingBy(Person::getAge));
        Iterator it = personGroups.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<Integer, List<Person>> persons = (Map.Entry)it.next();
            System.out.println("Age "+persons.getKey()+" = "+ persons.getValue().size());
        }
    }
    //照成年人和未成年人归组 partitioningBy 分组的条件只能是Boolean值
    private static void partitioningByDemo(){
        Map<Boolean, List<Person>> children = Stream.generate(new PersonSupplier())
                .limit(100)
                .collect(Collectors.partitioningBy(p -> p.getAge() < 18));
        System.out.println("Children number: "+children.get(true).size());
        System.out.println("Adult number: "+ children.get(false).size());
    }


//    public static void main(String[] args) {
//        partitioningByDemo();
//    }

    /**
     * 结束语：总之，Stream的特性可以归纳为
     * 1.不是数据结构
     * 2.它没有内部存储，它只是用操作管道从source（数据结构、数组、generator function、IO channel） 抓取数据
     * 3.它也绝不修改自己所封装的底层数据结构的数据。例如Stream的filter操作会产生一个不包含被过滤
     * 元素的新Stream，而不是从source删除那些元素。
     * 4.所有的Stream的操作必须以lambda表达式为参数
     * 5.不支持索引访问
     * 6.可以请求第一个元素，但无法请求第二个、第三个、或最后一个。不过请参阅下一项。
     * 7.很容易生成数组或者List
     * 8.惰性化
     * 9.很多Stream操作是向后延迟的，一直到它弄清楚了最后需要多少数据才会开始
     * 10.Intermediate操作永远是惰性化的。
     * 11.并行能力
     * 12.当一个Stream是并行化的，就不需要在写多线程代码，所以对它的操作会自动并行进行的
     * 13.可以是无限的
     *      1.集合有固定大小，Stream则不必。limit(n)和findFirst()这类的short-circuiting
     *          操作可以对无限的Stream进行运算并很快完成
     */


}
