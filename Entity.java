// Entity Class, parent class for things like Player and Enemy

class Entity {

   //stats
   private int HP;      //hit points
   private int STR;     //strength
   private int DEF;     //defense
   private int ACC;     //accuracy
   private int AVO;     //avoid
   
   //constructor
   public Entity(int hp, int str, int def, int acc, int avo){
      HP = hp;
      STR = str;
      DEF = def;
      ACC = acc;
      AVO = avo;
   }
   
   /*Pseudocode until other classes are implemented

   //armor get equiped//
   public void equip(Armor item){
      DEF += item.defense;
      if(item.acc != 0){
         ACC += item.acc;
      }
      if(item.avo != 0){
         AVO += item.avo;
      }
   }

   //item gets equiped//
   public void equip(Weapon item){
      STR += item.strength;
      if(item.acc != 0){
         ACC += item.acc;
      }
      if(item.avo != 0){
         AVO += item.avo;
      }
   }

   //take damage//
   public void damage(int damage){
      HP -= damage;
      if (HP <= 0){
         characterDiedMethod();
      }
   }
   
   //attack an enemy//
   *** Should be reused for enemies ***
   public int attack(Enemy enemy){
      int chance = ACC - enemy.avo;
      int damage = 0;
      if (range < 0){
         return damage;
      }else{
         int hit = random.nextInt(0, 101);
         if (hit <= chance){
            damage = STR - enemy.defense;
         }else{
            return damage;
         }
      }
      return damage;
   }

   */
}