import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    public ArrayList<Item> Items;

    public static void main(String[] args)
    {
        System.out.println("OMGHAI!");

        var app = new Program();
        app.Items = new ArrayList<Item>();
        app.Items.add(new Item("+5 Dexterity Vest", 10, 20));
        app.Items.add(new Item("Aged Brie", 2, 0));
        app.Items.add(new Item("Elixir of the Mongoose", 5, 7));
        app.Items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        app.Items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        app.Items.add(new Item("Conjured Mana Cake", 3, 6));

        app.UpdateQuality();

        System.out.println("Press enter to exit...");
        var scan = new Scanner(System.in);
        scan.nextLine();
        scan.close();
    }

    public void UpdateQuality()
    {
        for (var i = 0; i < Items.size(); i++)
        {
            var item = Items.get(i);
            if (!item.getName().equals("Aged Brie") && !item.getName().equals("Backstage passes to a TAFKAL80ETC concert"))
            {
                if (item.getQuality() > 0)
                {
                    if (!item.getName().equals("Sulfuras, Hand of Ragnaros"))
                    {
                        item.setQuality(item.getQuality() - 1);
                    }
                }
            }
            else
            {
                if (item.getQuality() < 50)
                {
                    item.setQuality(item.getQuality() + 1);

                    if (item.getName().equals("Backstage passes to a TAFKAL80ETC concert"))
                    {
                        if (item.getSellIn() < 11)
                        {
                            if (item.getQuality() < 50)
                            {
                                item.setQuality(item.getQuality() + 1);
                            }
                        }

                        if (item.getSellIn() < 6)
                        {
                            if (item.getQuality() < 50)
                            {
                                item.setQuality(item.getQuality() + 1);
                            }
                        }
                    }
                }
            }

            if (!item.getName().equals("Sulfuras, Hand of Ragnaros"))
            {
                item.setSellIn(item.getSellIn() - 1);
            }

            if (item.getSellIn() < 0)
            {
                if (!item.getName().equals("Aged Brie"))
                {
                    if (!item.getName().equals("Backstage passes to a TAFKAL80ETC concert"))
                    {
                        if (item.getQuality() > 0)
                        {
                            if (!item.getName().equals("Sulfuras, Hand of Ragnaros"))
                            {
                                item.setQuality(item.getQuality() - 1);
                            }
                        }
                    }
                    else
                    {
                        item.setQuality(item.getQuality() - item.getQuality());
                    }
                }
                else
                {
                    if (item.getQuality() < 50)
                    {
                        item.setQuality(item.getQuality() + 1);
                    }
                }
            }
        }
    }
}