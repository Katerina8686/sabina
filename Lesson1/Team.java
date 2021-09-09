package Lesson1;


public class Team {

    public static void main(String[] args) {
        Course c = new Course(new String[]{"one", "two", "three"}); // Создаем полосу препятствий
        Team team = new Team("my first team", new String[]{"Lilya", "Lola", "Kira", "Mira"}); // Создаем команду
        c.doIt(team); // Просим команду пройти полосу
        team.showResults(); // Показываем результаты
    }

    // getter
    public String getTeamName() {
        return teamName;
    }

    private String teamName;
    private String[] teamMembers;

    public Team(String teamName, String[] members) {
        this.teamName = teamName;
        this.teamMembers = members;
    }

    public void showResults() {
        System.out.println(this.teamName + " results: ..." + "");
    }
}
