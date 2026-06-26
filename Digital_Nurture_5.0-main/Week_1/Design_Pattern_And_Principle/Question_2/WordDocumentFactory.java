package Week_1.Design_Pattern_And_Principle.Question_2;

public class WordDocumentFactory extends DocumentFactory{
    @Override
    public Document createDocument() {
        return new WordDocument();
    }
}
