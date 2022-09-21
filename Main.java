import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] products = {"Молоко",
                "Сыр",
                "Хлеб",
                "Йогурт",
                "Свекла"
        };
        int[] price = {70, 400, 40, 88, 20};
        int[] yourBasket = new int[products.length];

        String[] saleProducts = {
                "Сахар",
                "Гречка",
                "Тушенка",
        };
        int[] salePrises = {
                80,
                60,
                40,
        };
        int[] saleNumb = new int[salePrises.length];
        int saleOllSum = 0;
        int saleProductNum = 0;
        int saleAmount;

        System.out.println("Список продуктов и стоимости единицы:");

        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + "." + products[i] + " " + price[i] + " руб.ед");
        }
        System.out.println(System.lineSeparator() + "Список товаров по акции 2 = 3: ");
        for (int j = 0; j < saleProducts.length; j++) {
            System.out.println((j + 6) + ". " + saleProducts[j] + " " + salePrises[j] + " руб.ед");
        }
        int total = 0;
        int productNum = 0;
        int amount = 0;

        while (true) {
            System.out.println(" Выберите товар и количество или введите end");
            String input = scanner.nextLine();
            if (input.equals("end")) {
                break;
            }

            String[] part = input.split(" ");
            if (part.length != 2) {
                System.out.println("Некорректный ввод! Нужно ввести два числа");
                continue;
            }

            try {
                productNum = Integer.parseInt(part[0]) - 1;
                amount = Integer.parseInt(part[1]);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка. Нужно вводить только числа");
                continue;
            }

            if ((productNum + 1) > (products.length + saleProducts.length) || productNum + 1 <= 0) {
                System.out.println("Ошибка.Нужно выбрать номер продукта из списка");
                continue;
            }

            if (productNum < products.length) {
                if (yourBasket[productNum] + amount < 0) {
                    System.out.println("Количество товара в корзине не может быть меньше 0");
                    continue;
                }
                if (amount == 0) {
                    total -= (yourBasket[productNum] * price[productNum]);
                    yourBasket[productNum] = 0;
                } else {
                    yourBasket[productNum] = yourBasket[productNum] + amount;
                    int sum = amount * price[productNum];
                    total += sum;
                }
            } else {
                if (amount == 3) {
                    saleAmount = amount;
                    saleNumb[saleProductNum] += saleAmount;
                    int sumS = (saleNumb[saleProductNum] * salePrises[saleProductNum]) - salePrises[saleProductNum];
                    saleOllSum += sumS;
                } else {
                    saleAmount = amount;
                    saleNumb[saleProductNum] += saleAmount;
                    int sumS = saleNumb[saleProductNum] * salePrises[saleProductNum];
                    saleOllSum += sumS;
                }
            }
            System.out.println("Итого:" + total);
            System.out.println("Ваша корзина с товарами по акции:");
            for (int m = 0; m < saleNumb.length; m++) {
                int currentPrice = yourBasket[m] * price[m];
                if (yourBasket[m] > 0) {
                    System.out.println(products[m] + " " + yourBasket[m] + " ед. " + currentPrice + " руб. в сумме");
                }
            }

            System.out.println("Итого по акции: " + saleOllSum + " руб");
            System.out.println(System.lineSeparator() + "Итого за все: " + (total + saleOllSum));


        }
        System.out.println("Итого (по акции): " + saleOllSum + "рублей");

        System.out.println("Общая сумма " + (total + saleOllSum) + " рублей");
    }
    }

