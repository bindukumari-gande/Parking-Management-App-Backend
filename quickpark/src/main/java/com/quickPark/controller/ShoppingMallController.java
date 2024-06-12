package com.quickPark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.quickPark.entity.Block;
import com.quickPark.entity.ShoppingMall;
import com.quickPark.entity.Slot;
import com.quickPark.service.ShoppingMallService;

@RestController
@RequestMapping("/shoppingMall")
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.PUT, RequestMethod.GET, RequestMethod.DELETE,
		RequestMethod.POST })
public class ShoppingMallController {

	@Autowired
	private ShoppingMallService mallService;

	@PostMapping("/addShoppingMall")
	public ResponseEntity<ShoppingMall> addShoppingMall(@RequestBody ShoppingMall mall) {
		return new ResponseEntity<ShoppingMall>(mallService.addShoppingMall(mall), HttpStatus.OK);
	}


	@PutMapping("/updateShoppingMall/{mallId}")
	public ResponseEntity<ShoppingMall> updateShoppingMall(@RequestBody ShoppingMall m, @PathVariable int mallId) {
		return new ResponseEntity<ShoppingMall>(mallService.updateShoppingMall(m, mallId), HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteShoppingMall/{mallId}")
	public ResponseEntity<Integer> deleteShoppingMall(@PathVariable("mallId") int mallId) {
		return new ResponseEntity<Integer>(mallService.deleteShoppingMall(mallId), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getAllShoppingMalls")
	public ResponseEntity<List<ShoppingMall>> getAllShoppingMalls() {
		return new ResponseEntity<List<ShoppingMall>>(mallService.getAllShoppingMalls(), HttpStatus.FOUND);
	}

	@GetMapping("{mallId}")
	public ResponseEntity<ShoppingMall> getShoppingMallById(@PathVariable int mallId) {
		return new ResponseEntity<ShoppingMall>(mallService.getShoppingMallByMallId(mallId), HttpStatus.OK);
	}

	@PostMapping("/addBlock/{mallId}")
	public ResponseEntity<Block> addNewBlock(@RequestBody Block block, @PathVariable("mallId") int mallId) {

		return new ResponseEntity<Block>(mallService.addBlock(block, mallId), HttpStatus.CREATED);
	}

	@PutMapping("updateblock/{blockId}")
	public ResponseEntity<Block> updateBlock(@RequestBody Block b, @PathVariable int blockId) {
		return new ResponseEntity<Block>(mallService.updateBlock(b, blockId), HttpStatus.CREATED);
	}

	@DeleteMapping("{blockId}")
	public void deleteBlock(@PathVariable int blockId) {

		mallService.deleteBlock(blockId);
	}

	@GetMapping("/viewAllblocks")
	public ResponseEntity<List<Block>> viewAllBlocks() {
		return new ResponseEntity<List<Block>>(mallService.viewAllBlocks(), HttpStatus.FOUND);
	}

	@GetMapping("viewBlocks/{mallId}/")
	public ResponseEntity<List<Block>> viewBlockByMallIId(@PathVariable int mallId) {
		return new ResponseEntity<List<Block>>(mallService.viewAllBlocksByShoppingMallId(mallId), HttpStatus.OK);
	}

	@PostMapping("/addSlot/{blockId}/{noOfSlots}")
	public ResponseEntity<Integer> addSlotByBlockId(@RequestBody Slot slot, @PathVariable int blockId,
			@PathVariable int noOfSlots) {

		return new ResponseEntity<Integer>(mallService.addSlot(slot, blockId, noOfSlots), HttpStatus.OK);
	}

	@PutMapping("updateSlot/{slotId}")
	public ResponseEntity<Slot> updateSlot(@RequestBody Slot slot, @PathVariable int slotId) {
		return new ResponseEntity<Slot>(mallService.updateSlot(slot, slotId), HttpStatus.CREATED);
	}

	@DeleteMapping("deleteslot/{slotId}")
	public void deleteSlot(@PathVariable int slotId) {

		mallService.deleteSlot(slotId);
	}

	@GetMapping("/viewAllSlots")
	public ResponseEntity<List<Slot>> viewAllSlots() {
		return new ResponseEntity<List<Slot>>(mallService.viewAllSlots(), HttpStatus.FOUND);
	}

	@GetMapping("viewSlots/{blockId}")
	public ResponseEntity<List<Slot>> viewSlotsByBlockId(@PathVariable int blockId) {
		return new ResponseEntity<List<Slot>>(mallService.viewAllSlotsByBlockId(blockId), HttpStatus.OK);
	}

}
