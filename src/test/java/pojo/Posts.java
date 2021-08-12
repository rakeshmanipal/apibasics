package pojo;

public class Posts {
    private String author;
    private int id;
    private String title;

    public Posts() {
    }

    public Posts(int id, String title,String author) {
        this.author = author;
        this.id = id;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public static class Builder {
        private String author;
        private int id;
        private String title;

       public Builder(){

       }

        public Builder setAuthor(String author) {
            this.author = author;
            return this;
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Posts build(){
           return new Posts(id,title,author);
        }

    }

}
