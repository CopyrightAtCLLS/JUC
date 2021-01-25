package stream;

import java.util.Arrays;
import java.util.List;

public class StreamDemo1 {

    /**
     * 1、id为偶数
     * 2、年龄大于21
     * 3、名字转大写
     * 4、名字倒叙排列
     * 5、只输出两个用户
     * @param args
     */
    public static void main(String[] args) {
        User user1=new User(1,"a",21);
        User user2=new User(2,"b",22);
        User user3=new User(3,"c",23);
        User user4=new User(4,"d",24);
        User user5=new User(5,"e",25);
        User user6=new User(6,"f",26);
        List<User> list= Arrays.asList(user1,user2,user3,user4,user5,user6);

        list.stream()
                .filter(user->{return user.getId()%2==0;})
                .filter(user->{return user.getAge()>21;})
                .map(user->{user.getUsername().toUpperCase();return user;})
                .limit(4)
                .sorted((big,small)->{return small.getUsername().compareTo(big.getUsername());})
                .forEach(System.out::println);
    }
}
class User{
    private int id;
    private String username;
    private int age;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                '}';
    }

    public User(int id, String username, int age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
