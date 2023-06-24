import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("[Калькулятор счёта 1.0]");
        Scanner scanner = new Scanner(System.in);
        int people = 0;
        while (true) { //очень долго пытался найти решение в практикуме,
            try { //но походу готовой сортировки по вводу букв/цифр нет. Позаимствовал try/catch
                System.out.println("1.Введите кол-во человек (Целое число >1)"); //из внешних
                people = Integer.parseInt(scanner.next());                       //ресурсов
                if (people > 1) {
                     break;
                } else {
                    System.out.println("Введено число <2. Счёт поделить нельзя.");
                }
            } catch (NumberFormatException iGuessItWasntAnInt) {//если ввели не int
                System.out.println("Введено не целое число.");
            }
        }
        String outcome = calculate(people);
        System.out.println("Кол-во людей: "+ people +"\nДобавленные товары:\n"+outcome);
    }

public static String calculate (int number){
    //Этот метод выдаст де-факто решение задачи в виде списка блюд+цен и сумма/кол-во людей (String)
    Scanner scanner = new Scanner(System.in).useDelimiter("\\n");//если бы писал код с 0
    System.out.println("2.Подсчёт суммы счёта и деление.");             //завел бы scanner  в Main
    String input;
    double price=0;
    String result="";
    double sum=0;
    int count=1;//решил проиндексировать блюда
    System.out.println("Введите название блюда №"+count+".");//опции на 0 блюд пользавателю не дам
    input=scanner.next();
    result="1."+input+" - ";
    while (true) {
        try {
            System.out.println("Введите цену блюда в рублях (Числа будут округлены до сотых)");
            price = Double.parseDouble(scanner.next());
            if (price > 0) {
                input=String.format("%.2f", price);
                sum=sum+Double.parseDouble(input); //сумма счёта
                result=result+input+roubles(Double.parseDouble(input))+".\n";
                break;
            } else {
                System.out.println("Числа <= 0 рублей недопустимы.");
            }
        } catch (NumberFormatException iGuessItWasntAnInt) {//если ввели не int
            System.out.println("Неверный формат ввода.");
        }
    }
    while (true){//цикл ввода содержания чека до "завершить"
        count++;
        System.out.println("Введите название блюда №"+count+" или \"Завершить\", " +
                "чтобы перейти к ");
        input=scanner.next();
        if (input.toLowerCase().equals("завершить")) {break;} //условие завершения цикла
        result=result+count+"."+input+" - ";
        while (true) { //внутренний цикл для проверки правильного ввода цены
            try {
                System.out.println("Введите цену блюда в рублях (Числа будут округлены до сотых)");
                price = Double.parseDouble(scanner.next());
                if (price > 0) {
                    input=String.format("%.2f", price);
                    sum=sum+Double.parseDouble(input);
                    result=result+input+roubles(Double.parseDouble(input))+".\n";
                    break;
                } else {
                    System.out.println("Числа <= 0 рублей недопустимы.");
                }
            } catch (NumberFormatException iGuessItWasntAnInt) {//если ввели не int
                System.out.println("Неверный формат ввода.");
            }
        }

    }
    result=result+"Общая стоимость блюд = "+sum+roubles(sum)+".\n";//Добавим сумму
    double costPerPerson = sum/number;
    String round = String.format("%.2f", costPerPerson); //в следующий раз буду использовать decimal
    costPerPerson= Double.parseDouble(round);            //даже если мы его не покроем в Практикуме.
    if ((costPerPerson*number)<sum) { //т.к. округление вверх без введения Math не сделать, а
        while ((costPerPerson*number)<sum) { //делать import того что мы не проходили
              costPerPerson=costPerPerson+0.01; //я ОЧЕНЬ не хочу - добиваем по копейке
        }                                           // пока не начнёт делиться ровно.
    }
    //нам обязательны падежи для рублей...
    result=result+"Округляя вверх до копейки, каждый участник должен заплатить "+
            String.format("%.2f", (costPerPerson))+roubles(costPerPerson)+".";
    return result;
}
public static String roubles (double amount){ //хозяин - барин, я бы поставил ₽
     int uno = (int)amount % 10;
     int decem = (int)amount % 100;
     if (decem>9 && decem<20)
        { return(" рублей");}
     else {switch (uno){
         case 1: return(" рубль");
         case 2:
         case 3:
         case 4:
             return(" рубля");        //компилятор подсказал! Сам бы забыл... :)
         default: return(" рублей");} //сообразил)
     }
     }


}
