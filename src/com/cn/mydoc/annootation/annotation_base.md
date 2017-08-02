#### 什么是注解（Annotation）：

######  Annotation（注解）就是Java提供了一种元程序中的元素关联任何信息和着任何元数据（metadata）的途径和方法。Annotion(注解)是一个接口，程序可以通过反射来获取指定程序元素的Annotion对象，然后通过Annotion对象来获取注解里面的元数据。

　　Annotation(注解)是JDK5.0及以后版本引入的。它可以用于创建文档，跟踪代码中的依赖性，甚至执行基本编译时检查。从某些方面看，**就像修饰符一样被使用**，并应用于**包、类 型、构造方法、方法、成员变量、参数、本地变量**的声明中。这些信息被存储在Annotation的“name=value”结构对中。

---

- 　**Annotation的成员在Annotation类型中以无参数的方法的形式被声明**。其方法名和返回值定义了该成员的名字和类型。在此有一个特定的默认语法：允许声明任何Annotation成员的默认值：一个Annotation可以将name=value对作为没有定义默认值的Annotation成员的值，当然也可以使用name=value对来覆盖其它成员默认值。这一点有些近似类的继承特性，父类的构造函数可以作为子类的默认构造函数，但是也可以被子类覆盖。

-  **Annotation能被用来为某个程序元素（类、方法、成员变量等）** 关联任何的信息。需要注意的是，这里存在着一个基本的规则：Annotation不能影响程序代码的执行，无论增加、删除 Annotation，代码都始终如一的执行。另外，尽管一些annotation通过java的反射api方法在运行时被访问，而java语言解释器在工作时忽略了这些annotation。正是由于java虚拟机忽略了Annotation，导致了annotation类型在代码中是“不起作用”的； 只有通过某种配套的工具才会对annotation类型中的信息进行访问和处理。本文中将涵盖标准的Annotation和meta-annotation类型，陪伴这些annotation类型的工具是java编译器（当然要以某种特殊的方式处理它们）。
------
### 什么是metadata（元数据）：

* 　元数据从metadata一词译来，就是“关于数据的数据”的意思。
* 　　元数据的功能作用有很多，比如：你可能用过Javadoc的注释自动生成文档。这就是元数据功能的一种。总的来说，元数据可以用来创建文档，跟踪代码的依赖性，执行编译时格式检查，代替已有的配置文件。如果要对于元数据的作用进行分类，目前还没有明确的定义，不过我们可以根据它所起的作用，大致可分为三类： 
  * 　1. 编写文档：通过代码里标识的元数据生成文档
  * 　2. 代码分析：通过代码里标识的元数据对代码进行分析
  * 　3. 编译检查：通过代码里标识的元数据让编译器能实现基本的编译检查
* 在Java中元数据以标签的形式存在于Java代码中，元数据标签的存在并不影响程序代码的编译和执行，它只是被用来生成其它的文件或针在运行时知道被运行代码的描述信息。
　　综上所述：
  * 第一，元数据以标签的形式存在于Java代码中。
  *  第二，元数据描述的信息是类型安全的，即元数据内部的字段都是有明确类型的。
  *  第三，元数据需要编译器之外的工具额外的处理用来生成其它的程序部件。
  *  第四，元数据可以只存在于Java源代码级别，也可以存在于编译之后的Class文件内部。
  *  
  

-----------
 ### Annotation和Annotation类型：

- **Annotation**：
    - Annotation使用了在java5.0所带来的新语法，它的行为十分类似public、final这样的修饰符。每个Annotation具有一个名字和成员个数>=0。每个Annotation的成员具有被称为name=value对的名字和值（就像javabean一样），name=value装载了Annotation的信息。

- **Annotation类型**：
    - Annotation类型定义了Annotation的名字、类型、成员默认值。一个Annotation类型可以说是一个特殊的java接口，它的成员变量是受限制的，而声明Annotation类型时需要使用新语法。当我们通过java反射api访问Annotation时，返回值将是一个实现了该 annotation类型接口的对象，通过访问这个对象我们能方便的访问到其Annotation成员。后面的章节将提到在java5.0的 java.lang包里包含的3个标准Annotation类型。
     
    

------


##### 注解的分类：

　　**根据注解参数的个数，我们可以将注解分为三类：**
- 　　　　1.标记注解:一个没有成员定义的Annotation类型被称为标记注解。这种Annotation类型仅使用自身的存在与否来为我们提供信息。比如后面的系统注解@Override;
- 　　　　2.单值注解
- 　　　　3.完整注解　　

　**根据注解使用方法和用途，我们可以将Annotation分为三类：**
- 　　　　1.JDK内置系统注解
- 　　　　2.元注解
- 　　　　3.自定义注解

-------

### 系统内置标准注解：

　　**注解的语法比较简单，除了@符号的使用外，他基本与Java固有的语法一致，JavaSE中内置三个标准注解，定义在java.lang中：**
- 　　　　@Override：用于修饰此方法覆盖了父类的方法;
- 　　　　@Deprecated：用于修饰已经过时的方法;
- 　　　　@SuppressWarnnings:用于通知java编译器禁止特定的编译警告。

　　下面我们依次看看三个内置标准注解的作用和使用场景。
　　
######　@Override，限定重写父类方法：

- 　　**@Override 是一个标记注解类型，它被用作标注方法**。它说明了被标注的方法重载了父类的方法，起到了断言的作用。如果我们使用了这种Annotation在一个没有覆盖父类方法的方法时，java编译器将以一个编译错误来警示。这个annotaton常常在我们试图覆盖父类方法而确又写错了方法名时发挥威力。使用方法极其简单：在使用此annotation时只要在被修饰的方法前面加上@Override即可。下面的代码是一个使用@Override修饰一个企图重载父类的displayName()方法，而又存在拼写错误的实例：
```
public class Fruit {

    public void displayName(){
        System.out.println("水果的名字是：*****");
    }
}

class Orange extends Fruit {
    @Override
    public void displayName(){
        System.out.println("水果的名字是：桔子");
    }
}

class Apple extends Fruit {
    @Override
    public void displayname(){          // 在这里会报错，试图覆盖父类的方法拼写错误
        System.out.println("水果的名字是：苹果");
    }
}
```
- 　 Orange 类编译不会有任何问题，Apple 类在编译的时候会提示相应的错误。@Override注解只能用于方法，不能用于其他程序元素。
@Deprecated，标记已过时：

- 　　同 样Deprecated也是一个标记注解。当一个类型或者类型成员使用@Deprecated修饰的话，编译器将不鼓励使用这个被标注的程序元素。而且这种修饰具有一定的 “延续性”：如果我们在代码中通过继承或者覆盖的方式使用了这个过时的类型或者成员，虽然继承或者覆盖后的类型或者成员并不是被声明为 @Deprecated，但编译器仍然要报警。
- 　值得注意，@Deprecated这个annotation类型和javadoc中的 @deprecated这个tag是有区别的：前者是java编译器识别的，而后者是被javadoc工具所识别用来生成文档（包含程序成员为什么已经过 时、它应当如何被禁止或者替代的描述）。

- 在java5.0，java编译器仍然象其从前版本那样寻找@deprecated这个javadoc tag，并使用它们产生警告信息。但是这种状况将在后续版本中改变，我们应在现在就开始使用@Deprecated来修饰过时的方法而不是 @deprecated javadoc tag。

**下面一段程序中使用了@Deprecated注解标示方法过期，同时在方法注释中用@deprecated tag 标示该方法已经过时，代码如下：**


```
class AppleService {
    public void displayName(){
        System.out.println("水果的名字是：苹果");
    }
    
    /**
     * @deprecated 该方法已经过期，不推荐使用
     */
    @Deprecated
    public void showTaste(){
        System.out.println("水果的苹果的口感是：脆甜");
    }
    
    public void showTaste(int typeId){
        if(typeId==1){
            System.out.println("水果的苹果的口感是：酸涩");
        }
        else if(typeId==2){
            System.out.println("水果的苹果的口感是：绵甜");
        }
        else{
            System.out.println("水果的苹果的口感是：脆甜");
        }
    }
}

public class FruitRun {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Apple apple=new Apple();
        apple.displayName();    
        
        AppleService appleService=new AppleService();
        appleService.showTaste();
        appleService.showTaste(0);
        appleService.showTaste(2);
    }

} 
```

AppleService类的showTaste() 方法被@Deprecated标注为过时方法，在FruitRun类中使用的时候，编译器会给出该方法已过期，不推荐使用的提示。　

---------------


### SuppressWarnnings，抑制编译器警告：

- @SuppressWarnings 被用于有选择的关闭编译器对类、方法、成员变量、变量初始化的警告。在java5.0，sun提供的javac编译器为我们提供了-Xlint选项来使编译器对合法的程序代码提出警告，此种警告从某种程度上代表了程序错误。例如当我们使用一个generic collection类而又没有提供它的类型时，编译器将提示出"unchecked warning"的警告。通常当这种情况发生时，我们就需要查找引起警告的代码。如果它真的表示错误，我们就需要纠正它。例如如果警告信息表明我们代码中的switch语句没有覆盖所有可能的case，那么我们就应增加一个默认的case来避免这种警告。
　　有时我们无法避免这种警告，例如，我们使用必须和非generic的旧代码交互的generic collection类时，我们不能避免这个unchecked warning。此时@SuppressWarning就要派上用场了，在调用的方法前增加@SuppressWarnings修饰，告诉编译器停止对此方法的警告。
- 　　SuppressWarning不是一个标记注解。它有一个类型为String[]的成员，这个成员的值为被禁止的警告名。对于javac编译器来讲，被-Xlint选项有效的警告 名也同样对@SuppressWarings有效，同时编译器忽略掉无法识别的警告名。
- 　　annotation语法允许在annotation名后跟括号，括号中是使用逗号分割的name=value对用于为annotation的成员赋值。实例如下：

```java
public class FruitService {
    
    @SuppressWarnings(value={ "rawtypes", "unchecked" })
    public static  List<Fruit> getFruitList(){
        List<Fruit> fruitList=new ArrayList();
        return fruitList;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static  List<Fruit> getFruit(){
        List<Fruit> fruitList=new ArrayList();
        return fruitList;
    }

    @SuppressWarnings("unused")
    public static void main(String[] args){
        List<String> strList=new ArrayList<String>();
    }
}

```

- 　　在这个例子中SuppressWarnings annotation类型只定义了一个单一的成员，所以只有一个简单的value={...}作为name=value对。又由于成员值是一个数组，故使用大括号来声明数组值。注意：我们可以在下面的情况中缩写annotation：当annotation只有单一成员，并成员命名为"value="。这时可以省去"value="。比如将上面方法getFruit()的SuppressWarnings annotation就是缩写的。

-  　 SuppressWarnings注解的常见参数值的简单说明：
    - 1.deprecation：使用了不赞成使用的类或方法时的警告；
    - 2.unchecked：执行了未检查的转换时的警告，例如当使用集合时没有用泛型 (Generics) 来指定集合保存的类型; 
    - 3.fallthrough：当 Switch 程序块直接通往下一种情况而没有 Break 时的警告;
    - 4.path：在类路径、源文件路径等中有不存在的路径时的警告; 
    - 5.serial：当在可序列化的类上缺少 serialVersionUID 定义时的警告; 
    - 6.finally：任何 finally 子句不能正常完成时的警告; 
    - 7.all：关于以上所有情况的警告。