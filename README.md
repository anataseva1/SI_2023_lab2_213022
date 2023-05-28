# Втора лабораториска вежба по Софтверско инженерство
## Ана Тасева, 213022
### Control Flow Graph

Еве како изгледа Control Flow графот за дадениот код.

![ControlFlowGraf](https://github.com/anataseva1/SI_2023_lab2_213022/assets/109041580/bf870596-5c18-4bc9-8e1f-ce7028de03af)


### Цикломатска комплексност

Цикломатската комплексност на овој граф е 11. Ова го гледаме според бројот на региони,кој е 11. Друг начин на кој може да одредиме е според колку предикатни јазли има + 1. Во овој случај имаме 10 и му додаваме плус едно и добиваме повторно 11.

### EveryBranch 

Според овој критериум минималниот број на тестови е 5. И тоа се:

1. Фрлање на исклучок
2. Username да е null, да имаме специјален карактер во лозинката, листата на корисници да е празна
3. Листата да има 2 корисника, едниот да се софпаѓа со user другиот не, да нема специјални карактери во лозинката
4. Лозинката на user да е помала од 8 карактери, email да е невалиден, листата да е празна
5. Лозинката да има празно место, email да е невалиден, листата да е празна

Пример test case за секој од тестовите:

1. user=null, allUsers = null -> враќа exception
2. user={username=null,password=Ana.Banana,email=ana.taseva56@gmail.com}, allUsers=null -> враќа true
3. user={username=Ana567,password=Softversko,email=ana.taseva56@gmail.com}, allUsers{(isto kako user),(username=Miki23,password=Inzenerstvo,email=MikiMilevski@gmail.com)} -> враќа false
4. user={username=Ana567,password=12345,email=ana123},allUsers=null -> враќа false
5. user={username=Ana567,password=Leto Petok,email=ana123},allUsers=null -> враќа false

Со овие тест примери е опфатен целиот код за тестирање. Со * е означено изминувањето на кодот според тест примерот.

![Screenshot_72](https://github.com/anataseva1/SI_2023_lab2_213022/assets/109041580/165cf3ad-1e82-4638-a23a-8d98db9313ab)


### Multiple Condition

Според овој критериум ни се потребни 4 тестови. Тоа се:

1. Кога првиот услов е точен,другите не се гледаат
2. Кога првиот е неточен, вториот е точен, третиот не се гледа
3. Кога првиот и вториот се неточни, а третиот е точен
4. Кога сите се неточни

Пример test case за секој од тестовите:

1. user=null, allUsers = null -> враќа exception
2. user={username=eli5,password=null,email=elipetrova@gmail.com}, allUsers=null -> враќа exception
3. user={username=eli5,password=12345678,email=null}, allUsers=null -> враќа exception
4. user={username=null,password=Ana.Banana,email=ana.taseva56@gmail.com}, allUsers=null -> враќа true

Претставено во табела би изгледало вака, Т(точно),F(неточно) и Х(билошто од двете) : 

![Screenshot_73](https://github.com/anataseva1/SI_2023_lab2_213022/assets/109041580/2b651c09-627c-43d6-8bc0-22b810307919)


### Unit Testing

Вака изгледа тест методата за EveryBranch: 

    @Test
    void everyBranch() { 
    //1
        RuntimeException ex;
        ex = assertThrows(RuntimeException.class,() -> SILab2.function(null, Collections.emptyList()));
        assertTrue(ex.getMessage().contains("Mandatory information missing!"));
    //2
        assertTrue(SILab2.function(new User(null, "Ana.Banana", "ana.taseva56@gmail.com"), Collections.emptyList()));
    //3
        assertFalse(SILab2.function(new User("Ana567", "Softversko", "ana.taseva56@gmail.com"), new ArrayList<>(List.of(
                new User("Ana567", "Softversko", "ana.taseva56@gmail.com"),
                new User("Miki23", "Inzenerstvo", "miki.milevski@gmail.com")))));
    //4
        assertFalse(SILab2.function(new User("Ana567", "12345", "ana123"),Collections.emptyList()));
    //5
        assertFalse(SILab2.function(new User("Ana567", "Leto Petok", "ana123"),Collections.emptyList()));
    }
   
Под 1 е претставено тестирањето за фрлање на исклучок, очекуваме да фрли исклучок со оваа порака, како аргументи праќаме за user null и празна листа.

Под 2 е тестирањето на додавање на username, ако како аргумент го пратиме да е null, тестираме лозинката да има специјален карактер и листата на корисници да биде празна. Очекуваме овој тест да ни врати true.

Под 3 проверуваме дали овој user веќе го има, во овој тест ќе ги изминеме случаевите и да го има и да го нема, со тоа што во листата ќе имаме двајца корисници. Исто така лозинката на корисникот нема да има специјален знак, ова значи дека очекуваме да ни врати false.

Под 4 тестираме дали тестот ќе ни врати false ако ја ставиме лозинката да е помала од 8 карактери, со невалиден email и праќаме празна листа на корисници.

Под 5 тестираме лозинката да има празно место во неа, очекуваме да ни врати false, имаме невалиден email и  праќаме празна листа на корисници.

Со ова се опфатените сите потребни тест примери за да се тестира програмата. 

Вака изгледа тест методата за MultipleCondition:

    @Test
    void multipleConditions(){
    //1
        RuntimeException ex;
        ex = assertThrows(RuntimeException.class,() -> SILab2.function(null, Collections.emptyList()));
        assertTrue(ex.getMessage().contains("Mandatory information missing!"));
    //2
        ex = assertThrows(RuntimeException.class,() -> SILab2.function(new User("eli5",null,"elipetrova@gmail.com"), Collections.emptyList()));
        assertTrue(ex.getMessage().contains("Mandatory information missing!"));
    //3
        ex = assertThrows(RuntimeException.class,() -> SILab2.function(new User("eli5","12345678",null), Collections.emptyList()));
        assertTrue(ex.getMessage().contains("Mandatory information missing!"));
    //4
        assertTrue(SILab2.function(new User(null, "Ana.Banana", "ana.taseva56@gmail.com"), Collections.emptyList()));
    }

Под 1 праќаме аргументи user да е null и празна листа, со ова ќе биде точен првиот услов и ќе влезе во if условот и очекуваме да се фрли исклучок.

Под 2 праќаме username и email на user, но лозинката ја ставаме да биде null и праќаме празна листа, со ова ќе падне првиот услов, но вториот ќе биде точно и пак ќе добиеме исклучок.

Под 3 праќаме username и password на user, но email-от го ставаме да биде null и праќаме празна листа, со ова ќе падне првиот и вториот услов, но третиот ќе биде точен и пак ќе добиеме исклучок.

Под 4 праќаме валиден user со сите негови променливи и празна листа, ова значи дека сите услови ќе паднат и нема да влеземе во if условот, според изминувањето на овој тест очекуваме да ни врати true. Замена на овој тест пример би била некоја друга патека со аргументи која би вратила false.

