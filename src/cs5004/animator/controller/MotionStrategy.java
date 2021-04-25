package cs5004.animator.controller;

/**
 * Strategy pattern interface.
 * It applies a motion, a move, a scale or a stay action to selected shape.
 */
public interface MotionStrategy {
  void makeMotion();
}
