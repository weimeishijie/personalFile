package com.personal.file.timer;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * 核心思路：新的API由三个核心思路组成
 * 1.不可改变值的类：一个严重的问题是，对于Java中已经存在的格式化处理类（Formatter）不是线程
 * 安全的。这使开发人员在日常开发中需要编写线程安全的日期处理代码变得很麻烦。新的API保证所有的
 * 核心类中的值是不可变的，避免了并发情况下带来的不必要的问题。
 * 2.领域驱动设计：新的API模型可以精确的表示出Date和Time的差异性。在以前的Java库中这一点就表现
 * 的非常差。比如。java.util.Date他表示一个时间点，从Unix时代开始就是以毫秒数的形式保存，但是你
 * 调用它的toString方法时，结果却显示它是有时区概念的，这就容易让开发者产生歧义。
 * 领域驱动设计的重点是从长远好处出发且简单易懂，当你需要把你以前的处理时间的模块代码移植到Java8
 * 上时你就需要考虑一下领域模型的设计了。
 * 3.区域化时间体系：新的API允许人们在时区不同的体系下使用。比如日本或者泰国，他们不必要遵循
 * ISO-8601。新API为大多数开发者减少了很多额外的负担，我们只需要使用标准的时间日期API
 */
public class Java8Time {

    /**
     * LocalDate类和LocalTime类（领域驱动设计）
     * LocalDate类和LocalTime类很有可能是你使用新API时第一个遇见的类。他们本地化的原因是他们
     * 能够根据系统环境来表示时间和日期，就像放在桌上的日历或者挂在墙上的时钟。还有一个混合类
     * 是LocalDate和LocalTime组合而成的，叫LocalDateTime.
     * 当你不知道你所运行环境的时区时，你应该使用这些本地化的类。比如桌面JavaFX程序就是其中
     * 之一。甚至可以在处于不同时区的分布式系统使用这些类。
     *
     * 现有的时间日期API中的类都不是线程安全的，开发者需要处理潜在的并发问题---这不是大部分开发者想要的
     *
     * 创建对象：
     * 在新的API中所有的核心类都可以由工厂方法很方便的构建。当我通过某些类自身的字段来构建它时
     * 可以使用of方法；当我通过从另外一个类型的转换来构建它时，可以使用from方法。同样也可以通过
     * parse方法来有一个String参数构建它：例如：
     */
    private static void localDateTimeDemo(){
        // LocalDateTime.now() : 创建一个当前的时间戳（类名直接创建时间）
        LocalDateTime timePoint = LocalDateTime.now();//The current date and time
        System.out.println(timePoint);
        // LocalDate.of() 创建一个时间（年月日）有很多重载（某些类型自身的字段构建时间）
        LocalDate localDate = LocalDate.of(2012, Month.DECEMBER, 12);// from values
        System.out.println(localDate);
        // LocalDate.ofEpochDay()此方法代表将传进来的数值（单位：天）加到1970-01-01上
        LocalDate localDate1 = LocalDate.ofEpochDay(150);// middle of 1970
        System.out.println(localDate1);
        // LocalTime.of() 创建一个时间（时分）有很多重载
        LocalTime localTime = LocalTime.of(17, 18); // the train I took home today
        System.out.println(localTime);
        // LocalTime.parse() : 将一个字符串（时间：时分秒）解析成localTime对象
        LocalTime localTime1 = LocalTime.parse("10:15:30");// From a String
        System.out.println(localTime1);
        LocalDate localDate2 = LocalDate.parse("2012-12-25");
        System.out.println(localDate2);
        // from接受一个其他类型行的时间，转换成当前时间对象
        LocalDate localDate3 = LocalDate.from(timePoint);
        System.out.println(localDate3);
    }

//    public static void main(String[] args){
//        localDateTimeDemo();
//    }

    /**
     * 在java8中我们可以使用Java标准的getter方法来获取想要的值（解剖一个时间戳）
     */
    private static void localDateDemo(){
        // LocalDateTime.now() : 创建一个当前的时间戳
        LocalDateTime timePoint = LocalDateTime.now();//The current date and time
        System.out.println(timePoint);
        // LocalDateTime.toLocalDate() 将时间戳转换成LocalDate对象
        LocalDate theDate = timePoint.toLocalDate();
        System.out.println(theDate);
        // LocalDateTime.getMonth() 得到时间戳中的月份
        Month month = timePoint.getMonth();
        System.out.println(month);
        // LocalDateTime.getDayOfMonth() 得到时间戳的月中天数（年中天数，周中天数）
        int day = timePoint.getDayOfMonth();
        System.out.println(day);
        // LocalDateTime.getSecond() 得到时间戳的秒数
        int second = timePoint.getSecond();
        System.out.println(second);
    }

//    public static void main(String[] args){
//        localDateDemo();
//    }

    /**
     * 也可以对 对象值进行运算操作。
     * 因为新的API中所有的类型都是不可变的，他们都是调用了with方法并返回一个新对象，
     * 相当于使用了setter赋值。对于每一个字段都有提供了基本的运算方法。
     */
    private static void localDateTimeDemo1(){
        // LocalDateTime.now() : 创建一个当前的时间戳
        LocalDateTime timePoint = LocalDateTime.now();//The current date and time
        System.out.println(timePoint);
        // set the value, returning a new object
        LocalDateTime thePast = timePoint.withDayOfMonth(10).withYear(2010);
        System.out.println(thePast);
        /*
         LocalDateTime.plusWeeks(long weeks)与
         LocalDateTime.puls(long amountToAdd, TemporalUnit unit)方法
         是相同的功效（为当前日期加上多少周）
         You can use direct manipulation methods,or pass a value and field pair
         */
        LocalDateTime yetAnother = thePast.plusWeeks(3).plus(3, ChronoUnit.WEEKS);
        System.out.println(yetAnother);
        // with方法在时间中相当于setter
    }

//    public static void main(String[] args) {
//        localDateTimeDemo1();
//    }

    /**
     * 新的API提供的一个调节器的概念-用来封装通用的处理逻辑的一段代码。你对任意时间使用WithAdjuster来
     * 设置一个或者多个字段，或者可以使用PlusAdjuster来对字段进行增量或者减法操作。值类型也可以被当做
     * 调节器使用，用来更新字段的值。新的API定义了一些内置的调节器，但是如果你希望实现某些特定的业务
     * 逻辑，你也可以自己实现一个调节器 例如：
     *
     * LocalDateTime timePoint = LocalDateTime.now();
     * LocalDateTime foo = timePoint.with(lastDayOfMonth());
     * LocalDateTime bar = timePoint.with(previousOrSame(ChronoUnit.WEDNESDAY));
     * timePoint.with(LocalTime.now());
     */
    private static void timeAndWith(){
        // LocalDateTime.now() : 创建一个当前的时间戳
        LocalDateTime timePoint = LocalDateTime.now();//The current date and time
        System.out.println(timePoint);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(timePoint.with(LocalTime.now()));
    }

//    public static void main(String[] args) {
//        timeAndWith();
//    }

    /**
     * 截取 ：
     * 新的API提供了表示不同精度的时间点类型来表示日期、时刻、时期+时刻。
     * API提供的truncatedTo(截取)方法使用于这种场景：他允许你从一个字段中截取出一个值
     * 例如：
     */
    private static void truncatedToDemo(){
        LocalDateTime time = LocalDateTime.now();
        System.out.println(time);
        //可以截取到值 DAYS(天)，HALF_DAYS(一天的一半)，HOURS(时)，MINUTES(分)，SECONDS(秒)
        // localTime.truncatedTo(ChronoUnit.SECONDS) 切除末尾的位数,
        LocalDateTime truncatedTime = time.truncatedTo(ChronoUnit.SECONDS);
        System.out.println(truncatedTime);
    }

//    public static void main(String[] args) {
//        truncatedToDemo();
//    }

    /**
     * 时区 ：
     * 参考了以前对于时区的很复杂的抽象方式。时区就是一个规则的集合，同样的时区遵守同样的时间准则
     * 规定，时区大约有40条规则。时区是由世界统一时间（UTC）来定义的。他们基本上是同步的但是也有细
     * 小的差别。时区有两种命名定义：简写型，比如，“PLT”，完整型，“Asia/Karachi.”。当你设计程序
     * 的时候，你应该考虑在什么时区下运行。
     *  ZoneId是时区的标识符。每一个ZoneId都表示这些时区遵循同样的规则。当你编码时可以考虑使用例如
     *  “PLT”，“Asia/Karachi,”这种字符串来创建ZoneId。
     *
     *  ZoneOffset是一段时间内代表格林尼治时间/世界同一时间与时区之间的差异。他能表示一个特殊的差异
     *  时区偏移量。
     *
     *  下面是一段完整的使用ZoneId的代码；
     *
     */
    private static void zoneIdDemo(){
        LocalDateTime dateTime = LocalDateTime.now();
        // You can specify the zone id when creating a zoned date time
        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println(id);
        ZonedDateTime zoned = ZonedDateTime.of(dateTime, id);
        System.out.println(zoned);
        assert id.equals(ZoneId.from(zoned));

//        ZoneOffset offset = ZoneOffset.of("+0:00");
//        System.out.println(offset);
    }

//    public static void main(String[] args) {
//        zoneIdDemo();
//    }

    /**
     * 时区类 ：
     * 1.ZonedDateTime是一个带有时区的日期类。他能表示出与任意时区的某个时间的时差。如果你希望代表一
     * 个日期和时间不依赖特定服务环境，你就应该使用ZonedDateTime
     *
     * 注意：Java中已经存在了表示时区的类—java.util.TimeZone—但是他不能用在Java SE 8中，
     * 因为在JSR-310中所有的时间日期类是不可变的，时区类确是可变的。
     */
    private static void zonedDateTimeDemo(){
        ZonedDateTime zonedDateTime = ZonedDateTime.parse("2007-12-03T10:15:30+01:00[Europe/Paris]");
        System.out.println(zonedDateTime);
    }

//    public static void main(String[] args) {
//        zonedDateTimeDemo();
//    }

    /**
     * 2.OffsetDateTime是一个带有偏移量的时间日期类。如果你的服务器处于不同的时区，他可以存入
     * 数据库中也可以用来记录某个准确的时间点
     * 3.OffsetTime是一个带有偏移量的时间类
     */
    private static void offsetDateTime(){
        OffsetTime time = OffsetTime.now();
        System.out.println(time);
        // changes offset, while keeping the same point on the timeline
//        OffsetTime sameTimeDifferentOffset = time.withOffsetSameInstant(offset);
        //changes the offset, and updates the point on the timeline
//        OffsetTime changeTimeWithNewoffset = time.withOffsetSameLocal(offset);
        // Can also create new object with altered fields as before
//        changeTimeWithNewoffset.withHour(3).plusSeconds(2);
    }

//    public static void main(String[] args) {
//        offsetDateTime();
//    }

    /**
     * Duration类
     * Duration类也是用来描述一段时间的，他和Period类似，但是不同于Period的是，它表示的精度更细；
     * 我们可以对其进行加减或者with函数操作，也可以使用它来修改一个时间日期对象的值
     *
     * JavaSE8的java.time包中新的时间日期API的功能可能性和安全性都大大提升了。新的API很好用，可以
     * 适应大部分场景。
     */
    private static void durationDemo(){
        Duration duration = Duration.ofSeconds(3, 5);
//        Duration oneDay = Duration.between(today, yesterday);
        System.out.println(duration);
    }

//    public static void main(String[] args) {
//        durationDemo();
//    }

    /**
     * Chronology系列类
     * 由于我们需要支持无ISO日期年表 表示的环境，JavaSE8首次引入了Chronology类，这种环境下使用。
     * 他们也实现了核心的时间日期接口
     * Chronology ：
     * - ChronoLocalDate
     * - ChronoLocalDateTime
     * - ChronoZonedDateTime
     * 这些类应该使用在具体高度国际化的应用中 需要使用本地化的时间体系时，没有这种需求的话最好不要
     * 使用他们。有一些时间体系中甚至没有一个月，一个星期的概念，我们只能通过更加通用抽象的字段进
     * 行运算。
     *
     * 其余的API改动 ：
     * JavaSE8还提供了一些其他场景下使用的类，MonthDay类表示一个月中的某几天，表示节日的时候可以
     * 使用。YearMonth类可以用来表示在比如信用卡的生效和失效时间（译者注：信用卡失效时间以月为单位）
     *
     * JavaSE8 中也提供了对于JDBC的新的类型支持。但是是一个非公开的修改。
     *
     * 这些类也可以和某些数据库类型进行映射。如下表，描述了这些类对于ANSI SQL的对应关系
     *
     *      ANSI SQL                --           Java SE 8
     *      Date                    --           LocalDate
     *      TIME                    --           LocalTime
     *    TIMESTAMP                 --           TIMESTAMP(时间戳)
     * TIMESTAMP WITH TIMEZONE      --           OffsetDateTime
     *
     * 总结 ：
     * javaSE8提供了java.time中新的日期和时间API。很大的提升了安全性，功能也更为强大。新的API架构
     * 模型，会让开发人员在各种场景下很方便的使用
     */








}



















