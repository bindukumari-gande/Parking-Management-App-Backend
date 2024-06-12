package com.quickPark.service;

import java.util.List;

import com.quickPark.entity.AuthoriseUser;
import com.quickPark.entity.Block;
import com.quickPark.entity.ShoppingMall;
import com.quickPark.entity.Slot;

public interface ShoppingMallService {

	public String authoriseShoppingMall(AuthoriseUser user);

	ShoppingMall addShoppingMall(ShoppingMall m);

	int deleteShoppingMall(int mallId);

	public ShoppingMall updateShoppingMall(ShoppingMall m, int mallId);

	List<ShoppingMall> getAllShoppingMalls();

	public ShoppingMall getShoppingMallByMallId(int mallId);

	Block addBlock(Block b, int mallId);

	public void deleteBlock(int blockId);

	public List<Block> viewAllBlocks();

	public Block updateBlock(Block b, int blockId);

	public List<Block> viewAllBlocksByShoppingMallId(int shoppingMallId);

	public int addSlot(Slot slot, int blockId, int noOfSlots);

	public Slot updateSlot(Slot slot, int slotId);
	
	public void deleteSlot(int slotId);
	
	public List<Slot> viewAllSlots();

	public List<Slot> viewAllSlotsByBlockId(int blockId);

}



