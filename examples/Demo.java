package examples;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Demo {

    //交互输入
    private static final Scanner SCANNER = new Scanner(System.in);

    //文件操作根目录
    private static final Path WORK_DIR = Paths.get("./demo");
    private static final Path FILE = WORK_DIR.resolve("data.txt");

    public static void main(String[] args) throws IOException {
        System.out.println("===============JAVA Demo 开始==================");

        stringOperations();
        numberOperations();
        fileOperations();
        arrayOperations();
        collectionOperations();
        interactiveInput();

        System.out.println("\n===============JAVA Demo 结束==================");
        SCANNER.close();
    }

    // 字符串操作
    private static void stringOperations() {
        System.out.println("--------------字符串操作--------------");

        String str = "  Hello, Java world!  ";
        System.out.println("原始字符串: ["+str+"]");

        //去空格(strip 支持 Unicode 空白字符,比 trim 更严谨)
        String trimmed = str.strip();
        System.out.println("去首尾空格:["+trimmed+"]");

        //大小写
        System.out.println("转大写: "+str.toUpperCase());
        System.out.println("转小写: "+str.toLowerCase());

        //长度与字符
        System.out.println("字符长度: "+str.length());
        System.out.println("第一个字符: "+str.charAt(0));

        //查找与替换
        System.out.println("是否包含Java: "+str.contains("Java"));
        System.out.println("indexOf Java: "+str.indexOf("Java"));
        System.out.println("替换Java->PHP: "+str.replace("Java", "PHP"));

        //截取与分割
        System.out.println("截取 [0,5): "+str.substring(0,5));
        String[] parts = str.split(",");
        System.out.println("按,分割: "+Arrays.toString(parts));

        //拼接
        System.out.println("拼接: "+String.join("-","A","B","C"));

        //格式化
        System.out.println("格式化: "+String.format("姓名=%s,年龄=%d,分数=%.2f","张三",25,92.5));

        //字符串反转
        String reversed = new StringBuilder(trimmed).reverse().toString();
        System.out.println("反转: "+reversed);

        //判断空字符串
        String empty = "";
        System.out.println("空字符串判断: "+empty.isEmpty());

        //Stream处理: 过滤+收集
        List<String> words = Arrays.asList("apple","banana","avocado","cherry");
        List<String> filtered = words.stream()
            .filter(w -> w.startsWith("a"))
            .map(String::toUpperCase)
            .collect(Collectors.toList());
        System.out.println("以a开头并转大写: "+filtered);
        System.out.println();
    }

    //数值操作
    private static void numberOperations() {
        System.out.println("-----------------数值操作---------------");

        int a = 17, b = 5;
        System.out.println("a = "+a+", b = "+b);
        System.out.println("加: "+(a+b));
        System.out.println("减: "+(a-b));
        System.out.println("乘: "+(a*b));
        System.out.println("除(整数): "+(a/b));
        System.out.println("取余: "+(a%b));
        System.out.println("除(浮点): "+((double) a/b));

        //自增自减
        int c = 10;
        System.out.println("c++后: "+(c++)+", 当前c = "+c);
        System.out.println("--c后: "+(--c));

        //最大值/最小值
        System.out.println("最大值: "+Math.max(a,b));
        System.out.println("最小值: "+Math.min(a,b));

        //幂,绝对值,平方根
        System.out.println("2^10: "+Math.pow(2, 10));
        System.out.println("abs(-99): "+Math.abs(-99));
        System.out.println("sqrt(144): "+Math.sqrt(144));

        //四舍五入与取整
        double d = 3.14159;
        System.out.println("round: "+Math.round(d));
        System.out.println("ceil: "+Math.ceil(d));
        System.out.println("floor: "+Math.floor(d));

        //随机数(ThreadLocalRandom 无锁竞争,单线程/并发场景都优于共享的 Random)
        int randInt = ThreadLocalRandom.current().nextInt(100); //0-99
        System.out.println("随机数(0~99): "+randInt);

        //大数运算
        BigInteger big1 = new BigInteger("12345678901234567890");
        BigInteger big2 = new BigInteger("98765432109876543210");
        System.out.println("BigInteger 相加: "+big1.add(big2));

        //精度问题,使用BigDecimal
        BigDecimal bd1 = new BigDecimal("0.1");
        BigDecimal bd2 = new BigDecimal("0.2");
        System.out.println("0.1+0.2 (double): "+(0.1+0.2));
        System.out.println("0.1+0.2 (BigDecimal): "+bd1.add(bd2));

        //类型转换
        String numStr = "2026";
        int parsed = Integer.parseInt(numStr);
        System.out.println("字符串转int: "+(parsed+1));
        System.out.println("int转字符串: "+String.valueOf(parsed)+"1");

        //进制转换
        System.out.println("十进制255->十六进制: "+Integer.toHexString(255));
        System.out.println("十进制255->二进制: "+Integer.toBinaryString(255));
        System.out.println();
    }

    //数组操作
    private static void arrayOperations() {
        System.out.println("-----------------数组操作----------------");

        //一维数组:声明与初始化
        int[] arr = {3,4,5,9,7,8,3};
        System.out.println("原始数组: "+ Arrays.toString(arr));

        //遍历
        System.out.println("for 遍历:");
        for(int i = 0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
        System.out.println("for 增强:");
        for(int v:arr){
            System.out.println(v);
        }
        System.out.println();

        //求和、平均值、最大、最小(空数组做边界保护,避免下标越界与除零)
        int sum = 0;
        int max = arr.length > 0 ? arr[0] : 0;
        int min = arr.length > 0 ? arr[0] : 0;
        for(int v: arr){
            sum += v;
            if(v>max) max = v;
            if(v<min) min = v;
        }
        double avg = arr.length == 0 ? 0 : (double) sum/arr.length;
        System.out.println("求和:"+sum);
        System.out.println("最大: "+max+",最小: "+min);
        System.out.println("平均值: "+avg);

        //排序(升序)
        int[] sorted = Arrays.copyOf(arr,arr.length);
        Arrays.sort(sorted);
        System.out.println("升序排序: "+Arrays.toString(sorted));

        //降序(需包装Integer)
        Integer[] boxed = Arrays.stream(arr).boxed().toArray(Integer[]::new);
        Arrays.sort(boxed,Collections.reverseOrder());
        System.out.println("降序排序: "+Arrays.toString(boxed));

        //查找(二分查找要求已排序)
        int idx = Arrays.binarySearch(sorted, 8);
        //未命中时返回负数(插入点取反),需判断后再作为下标使用
        System.out.println("元素8在升序数组中的下标: "+(idx >= 0 ? String.valueOf(idx) : "未找到"));

        //复制
        int[] copy = Arrays.copyOf(arr, arr.length);
        System.out.println("复制数组: "+Arrays.toString(copy));

        //填充
        int[] filled = new int[5];
        Arrays.fill(filled,66);
        System.out.println("填充数组: "+Arrays.toString(filled));

        //比较
        //比较(数组必须用 Arrays.equals 比较内容,equals 比较的是引用地址)
        System.out.println("arr 与 copy 是否相等: "+Arrays.equals(arr, copy));

        //二维数组
        int[][] matrix = {
            {1,2,3},
            {4,5,6},
            {7,8,9},
        };
        System.out.println("二维数组:");
        for(int[] row : matrix){
            System.out.println(Arrays.toString(row));
        }

        //二维数组求和
        int mSum = 0;
        for(int[] row : matrix){
            for(int v:row) mSum += v;
        }
        System.out.println("二维数组求和: "+mSum);

        //转置矩阵
        //转置矩阵(按实际行列数计算,避免写死维度导致越界)
        int rowCount = matrix.length;
        int colCount = rowCount == 0 ? 0 : matrix[0].length;
        int[][] transposed = new int[colCount][rowCount];
        for(int i=0;i<rowCount;i++){
            for(int j=0;j<matrix[i].length;j++){
                transposed[j][i] = matrix[i][j];
            }
        }
        System.out.println("转置矩阵:");
        for(int[] row:transposed){
            System.out.println(Arrays.toString(row));
        }

        //Stream操作数组
        int[] evens = Arrays.stream(arr).filter(x->x%2==0).toArray();
        System.out.println("筛选偶数: "+Arrays.toString(evens));

        int sSum = Arrays.stream(arr).sum();
        System.out.println("Stream 求和: "+sSum);

        //数组转List
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        System.out.println("数组转List: "+list);

        //List转数组
        int[] back = list.stream().mapToInt(Integer::intValue).toArray();
        System.out.println("List转数组: "+Arrays.toString(back));

        System.out.println();

    }

    //集合操作List/Set/Map
    private static void collectionOperations(){
        System.out.println("--------------集合操作(List/Set/Map)----------------");

        //List
        System.out.println("List操作");
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("cherry");
        list.add("apple"); //允许重复
        System.out.println("List新增后: "+list);

        //查
        System.out.println("size: "+list.size());
        System.out.println("get(1): "+list.get(1));
        System.out.println("contains banana: "+list.contains("banana"));
        System.out.println("indexOf apple: "+list.indexOf("apple"));
        System.out.println("isEmpty: "+list.isEmpty());

        //改
        list.set(1,"blueberry");
        System.out.println("set(1,blueberry): "+list);

        //删
        list.remove("apple"); //删除第一个匹配到元素
        System.out.println("remove apple 后: "+list);
        list.remove(0); //按下标删除
        System.out.println("remove(0) 后: "+list);

        //遍历三种方式
        System.out.println("for 遍历:");
        for(int i=0;i<list.size();i++) System.out.println(list.get(i));
        System.out.println();

        System.out.println("for 增强:");
        for(String s:list) System.out.println(s);
        System.out.println();

        System.out.println("forEach:");
        list.forEach(s -> System.out.println(s));
        System.out.println();


        //排序与去重
        list.add("apple");
        list.add("banana");
        Collections.sort(list);
        System.out.println("排序后: "+list);

        List<String> unique = new ArrayList<>(new LinkedHashSet<>(list));
        System.out.println("去重后: "+unique);

        //与数组转换
        String[] arr = list.toArray(new String[0]);
        System.out.println("List转数组: "+Arrays.toString(arr));
        System.out.println("数组转List: "+Arrays.asList(arr));

        //Stream操作
        List<String> upper = list.stream()
            .filter(s -> s.length() > 5)
            .map(String::toUpperCase)
            .collect(Collectors.toList());
        System.out.println("长度>5并转大写: "+upper);

        //Set
        System.out.println("Set操作");
        Set<String> set = new HashSet<>();
        set.add("apple");
        set.add("banana");
        set.add("apple"); //自动去重
        set.add("cherry");
        System.out.println("HashSet: "+set);
        System.out.println("size: "+set.size());
        System.out.println("contains apple: "+set.contains("apple"));

        set.remove("banana");
        System.out.println("remove banana 后: "+set);

        //保持插入顺序
        Set<String> linkedSet = new LinkedHashSet<>(Arrays.asList("C","A","B","A"));
        System.out.println("LinkedHashSet (保序去重): "+linkedSet);

        //自然排序
        Set<String> treeSet = new TreeSet<>(Arrays.asList("C","A","B","A"));
        System.out.println("TreeSet (排序去重): "+treeSet);

        //集合运算
        Set<Integer> s1 = new HashSet<>(Arrays.asList(1,2,3,4));
        Set<Integer> s2 = new HashSet<>(Arrays.asList(3,4,5,6));

        Set<Integer> union = new HashSet<>(s1);
        union.addAll(s2);
        System.out.println("并集: "+union);

        Set<Integer> intersection = new HashSet<>(s1);
        intersection.retainAll(s2);
        System.out.println("交集: "+intersection);

        Set<Integer> diff = new HashSet<>(s1);
        diff.removeAll(s2);
        System.out.println("差集(s1-s2): "+diff);


        //Map
        System.out.println("Map操作");
        Map<String,Integer> map = new HashMap<>();
        map.put("apple",10);
        map.put("banana",20);
        map.put("cherry",30);
        map.put("apple",15); //key相同,覆盖旧值
        System.out.println("HashMap: "+map);

        //查
        System.out.println("size: "+map.size());
        System.out.println("get apple: "+map.get("apple"));
        System.out.println("containsKey banana: "+map.containsKey("banana"));
        System.out.println("containsValue 30: "+map.containsValue(30));
        System.out.println("getOrDefault grape: "+map.getOrDefault("grape", 0));

        //删
        map.remove("banana");
        System.out.println("remove banana 后: "+map);

        //遍历
        System.out.println("keySet 遍历:");
        for(String key:map.keySet()){
            System.out.println(key+" = "+map.get(key));
        }
        System.out.println();

        System.out.println("entrySet 遍历:");
        for(Map.Entry<String,Integer> entry: map.entrySet()){
            System.out.println(entry.getKey() + " = "+entry.getValue());
        }
        System.out.println();

        System.out.println("forEach 遍历:");
        map.forEach((k,v)->System.out.println(k+" = "+v));

        //values集合
        System.out.println("所有值: "+map.values());

        //统计: compute / merge
        Map<String,Integer> counter = new HashMap<>();
        String[] words = {"apple","banana","apple","cherry","banana","apple"};
        for(String w:words){
            counter.merge(w,1,Integer::sum); //出现次数+1
        }
        System.out.println("词频统计: "+counter);

        //按值排序
        List<Map.Entry<String,Integer>> sortedEntries = new ArrayList<>(counter.entrySet());
        sortedEntries.sort((e1,e2)->Integer.compare(e2.getValue(), e1.getValue()));
        System.out.println("按词频排序: "+sortedEntries);

        //保序/排序 Map
        Map<String,Integer> linkedMap = new LinkedHashMap<>();
        linkedMap.put("C",3);
        linkedMap.put("A",1);
        linkedMap.put("B",2);
        System.out.println("LinkedHashMap (保序插入): "+linkedMap);

        Map<String,Integer> treeMap = new TreeMap<>(linkedMap);
        System.out.println("TreeMap (按key排序):"+treeMap);
        System.out.println();

    }

    private static void fileOperations() throws IOException {
        System.out.println("---------------------文件操作-----------------");

        //创建目录(createDirectories 本身幂等,先 exists 再创建存在并发竞态,直接调用即可)
        Files.createDirectories(WORK_DIR);
        System.out.println("[创建目录]: "+ WORK_DIR.toAbsolutePath());

        //增:写入文件
        List<String> lines = Arrays.asList("apple","banana","cherry");
        Files.write(FILE,lines,StandardCharsets.UTF_8,StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING);
        System.out.println("[新增] 文件:"+FILE.toAbsolutePath()+",写入内容:"+lines);

        //查:读取文件
        List<String> read = Files.readAllLines(FILE,StandardCharsets.UTF_8);
        System.out.println("[查询] 读取内容: "+read);

        //改:追加一行
        Files.write(FILE,Collections.singletonList("dragonfruit"),StandardCharsets.UTF_8,StandardOpenOption.APPEND);
        System.out.println("[修改] 追加一行后: "+Files.readAllLines(FILE));

        //改:替换内容
        String content = new String(Files.readAllBytes(FILE),StandardCharsets.UTF_8);
        content = content.replace("banana","blueberry");
        Files.write(FILE,content.getBytes(StandardCharsets.UTF_8),StandardOpenOption.TRUNCATE_EXISTING);

        //查:文件属性
        System.out.println("[查询] 文件大小: "+Files.size(FILE)+"字节");
        System.out.println("[查询] 文件是否存在: "+Files.exists(FILE));

        //删:删除文件
        boolean deleted = Files.deleteIfExists(FILE);
        System.out.println("[删除] 文件是否删除成功: "+deleted);
        System.out.println("[删除] 删除后文件是否存在: "+Files.exists(FILE));

        //删:删除目录
        Files.deleteIfExists(WORK_DIR);
        System.out.println("[删除] 删除目录后目录是否存在: "+Files.exists(WORK_DIR));
        System.out.println();
    }

    //交互操作
    private static void interactiveInput() {
        System.out.println("-------------交互输入输出-------------");

        //输入字符串
        System.out.print("请输入你的名字: ");
        String name = SCANNER.nextLine();
        System.out.println("你好, "+name+"!");

        //输入整数
        System.out.print("请输入你的年龄: ");
        int age;
        while(true){
            while(!SCANNER.hasNextInt()){
                System.out.print("输入的不是整数,请重新输入: ");
                SCANNER.next();
            }
            age = SCANNER.nextInt();
            //边界校验:年龄需在合理区间,否则要求重新输入
            if(age >= 0 && age <= 150){
                break;
            }
            System.out.print("年龄需在 0~150 之间,请重新输入: ");
        }
        String flag = "";
        if(age <= 7){
            flag = "祖国的小花朵";
        } else if (age < 18){
            flag = "旭日朝阳";
        } else if (age < 30){
            flag = "年轻有为";
        } else if (age < 60){
            flag = "年富力强";
        } else if (age < 70){
            flag = "发光发热";
        } else {
            flag = "颐养天年";
        }
        System.out.println("你输入的年龄是: "+age+" ["+flag+"]");

        //输入浮点数
        System.out.print("请输入你的身高(cm): ");
        double height;
        while(true){
            while(!SCANNER.hasNextDouble()){
                System.out.print("请输入小数: ");
                SCANNER.next();
            }
            height = SCANNER.nextDouble();
            //边界校验:身高需在合理区间,否则要求重新输入
            if(height > 0 && height <= 300){
                break;
            }
            System.out.print("身高需在 0~300cm 之间,请重新输入: ");
        }
        System.out.println("你的身高是: "+height+"cm");

        //输入布尔值
        System.out.print("今天心情好么?(true/false): ");
        while(!SCANNER.hasNextBoolean()){
            System.out.print("请输入true或false: ");
            SCANNER.next();
        }

        boolean mood = SCANNER.nextBoolean();
        System.out.println("你的心情: "+(mood ? "😊 开心" : "😐 一般"));

        //输入多个整数(以空格分隔)
        SCANNER.nextLine(); //清空缓冲区
        System.out.print("请输入多个整数(空格分隔,例:1 2 3):");
        String line = SCANNER.nextLine().trim();
        List<Integer> nums = new ArrayList<>();
        //空输入跳过解析,避免 split 产生空串被误判为非法输入
        for(String t: line.isEmpty() ? new String[0] : line.split("\\s+")){
            try {
                nums.add(Integer.parseInt(t));
            } catch (NumberFormatException e){
                System.out.println("跳过非法输入: "+t);
            }
        }
        System.out.println("解析结果: "+nums);

        //让用户选择是否继续
        System.out.print("\n是否继续后续演示?(y/n): ");
        //用 equalsIgnoreCase 忽略大小写,避免 toLowerCase() 在特定 Locale(如土耳其语)下转换异常
        String choice = SCANNER.nextLine().trim();
        if(!choice.equalsIgnoreCase("y")){
            System.out.println("跳过后续演示,程序即将结束.");
            return; //直接返回由 main 统一收尾,不用 System.exit 以免跳过结束提示
        }
        System.out.println("很好,我们继续旅程...");
        System.out.println();
    }
}
