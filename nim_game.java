package mini_games;
import java.util.*;
public class nim_game {
    public static void printStacks(ArrayList<Integer> array){
        System.out.print("Your turn to choose \nCurrent Stacks: ");
        for(int i =0;i<array.size();i++){
            System.out.printf("%d ", array.get(i));
        }
        System.out.println("");

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String intro = "Hello! \nWelcome to The-Nim-Game! \nChoose number of stacks of coins you want to play with: ";
        System.out.println(intro);
        int n = sc.nextInt();
        while(n>8){
            System.out.println("Uhh, playing with more than 8 stacks would be boring honestly... any other number? ");
            n = sc.nextInt();
        }
        sc.nextLine();
        System.out.printf("Enter %d non-zero integers: \n",n);
        ArrayList<Integer> gameArray = new ArrayList<Integer>(n);
        int xor  = 0;
        for(int i=0;i<n;i++){
            gameArray.addLast(sc.nextInt());
            xor = xor^gameArray.get(i);
        }
        String rules = "Ok so we have stacks with the following number of coins.. starting with Stack Number 1:";
        System.out.println(rules);
        for(int i = 0;i<n;i++){
            System.out.printf("%d ",gameArray.get(i));
        }
        System.out.println("\n");
        rules = "Here are the rules: \nYou can choose a stack and remove any number of coins from it, atmax emptying it and then the chance transfers to me and I repeat the same\n";
        System.out.println(rules);
        rules = "For removing x coins from Stack number n, type.. n x";
        System.out.println(rules);
        rules = "For emptying a stack, type.. n 0\n";
        System.out.println(rules);
        rules = "Ok so now, would you like to make the first move? or Should I? ;)";
        System.out.println(rules);
        System.out.println("Enter 1 to start first, and 0 to start second: ");
        int ans = sc.nextInt();
        if(xor==0){
            if(ans==0){
                System.out.println("Damn. Good choice.");
                gameArray.set(0, gameArray.get(0)-1);
                if(gameArray.get(0)==0){gameArray.remove(0);}
                System.out.println("I removed 1 coin from the first stack");
                printStacks(gameArray);
            }
            else{
                System.out.println("Haha, rookie mistake");
            }
        }
        else{
            if(ans==1){
                System.out.println("Let's see how it turns out to be!");
            }
            else{
                System.out.println("Haha, I ain't losing this one");
                for(int i = 0;i<gameArray.size();i++){
                    if(gameArray.get(i)>=xor){
                        gameArray.set(i,gameArray.get(i)-xor);
                        if(gameArray.get(i)==0){gameArray.remove(i);}
                        System.out.printf("Removed %d coins from stack number %d \n",xor,i+1);
                        printStacks(gameArray);
                        break;
                    }
                }
            }
        }
        sc.nextLine();
        System.out.println();
        if(ans==1){printStacks(gameArray);}
        int a,b;
        while(gameArray.size()!=0){
            a = sc.nextInt();
            b = sc.nextInt();
            if(a<1 || a>gameArray.size()){
                System.out.println("Invalid Input");
                continue;
            }
            if(b<0 || b>gameArray.get(a-1)){
                System.out.println("Invalid Input");
                continue;
            }
            if(b==0 || b==gameArray.get(a-1)){
                gameArray.remove(a-1);
            }
            else{
                gameArray.set(a-1, gameArray.get(a-1)-b);
            }
            if(gameArray.size()==0){
                System.out.println("Congrats! You Won, left me with no moves to make");
                sc.close();
                return;
            }
            xor = 0;
            for(int i = 0;i<gameArray.size();i++){
                xor = xor^gameArray.get(i);
            }
            if(xor==0){
                gameArray.set(0, gameArray.get(0)-1);
                if(gameArray.get(0)==0){gameArray.remove(0);}
                System.out.println("I removed 1 coin from the first stack");
                printStacks(gameArray);

                continue;
            }
            else{
                for(int i = 0;i<gameArray.size();i++){
                    if((gameArray.get(i)^xor)<gameArray.get(i)){
                        System.out.printf("Removed %d coins from stack number %d \n",gameArray.get(i)-(gameArray.get(i)^xor),i+1);
                        gameArray.set(i,gameArray.get(i)^xor);
                        if(gameArray.get(i)==0){gameArray.remove(i);}
                        printStacks(gameArray);
                        break;
                    }
                }
            }
        }
        System.out.println("I WON! lol better luck next time :)");
        sc.close();
    }
}



