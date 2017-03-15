package com.project.onepice.basicproject.androidBasic;

/**
 * Created by onepice2015 on 16/5/30.
 */

/**
 * 做一个View嵌套的例子，嵌套滑动和点击
 * */
public class EventExample {

}
/**
 *当手指触摸到屏幕时，系统就会调用相应View的onTouchEvent，并传入一系列的action。

 dispatchTouchEvent的执行顺序为：

 首先触发ACTIVITY的dispatchTouchEvent,然后触发ACTIVITY的onUserInteraction
 然后触发LAYOUT的dispatchTouchEvent，然后触发LAYOUT的onInterceptTouchEvent
 这就解释了重写ViewGroup时必须调用super.dispatchTouchEvent();

 (1)dispatchTouchEvent:

 此方法一般用于初步处理事件，因为动作是由此分发，所以通常会调用super.dispatchTouchEvent。这样就会继续调用onInterceptTouchEvent，再由onInterceptTouchEvent决定事件流向。

 (2)onInterceptTouchEvent:

 若返回值为true事件会传递到自己的onTouchEvent();若返回值为false传递到下一个View的dispatchTouchEvent();

 (3)onTouchEvent():

 若返回值为true，事件由自己消耗，后续动作让其处理；若返回值为false，自己不消耗事件了，向上返回让其他的父View的onTouchEvent接受处理

 三大方法关系的伪代码：如果当前View拦截事件，就交给自己的onTouchEvent去处理，否则就丢给子View继续走相同的流程。
 *
 * onTouchEvent的传递：

 当有多个层级的View时，在父层级允许的情况下，这个action会一直传递直到遇到最深层的View。所以touch事件最先调用的是最底层View的onTouchEvent，如果View的onTouchEvent接收到某个touch action并做了相应处理，最后有两种返回方式return true和return false；return true会告诉系统当前的View需要处理这次的touch事件，以后的系统发出的ACTION_MOVE,ACTION_UP还是需要继续监听并接收的，并且这次的action已经被处理掉了，父层的View是不可能触发onTouchEvent的了。所以每一个action最多只能有一个onTouchEvent接口返回true。如果返回false，便会通知系统，当前View不关心这一次的touch事件，此时这个action会传向父级，调用父级View的onTouchEvent。但是这一次的touch事件之后发出任何action，该View都不在接受，onTouchEvent在这一次的touch事件中再也不会触发，也就是说一旦View返回false，那么之后的ACTION_MOVE,ACTION_UP等ACTION就不会在传入这个View,但是下一次touch事件的action还是会传进来的。

 父层的onInterceptTouchEvent

 前面说了底层的View能够接收到这次的事件有一个前提条件：在父层允许的情况下。假设不改变父层级的dispatch方法，在系统调用底层onTouchEvent之前会调用父View的onInterceptTouchEvent方法判断，父层View是否要截获本次touch事件之后的action。如果onInterceptTouchEvent返回了true，那么本次touch事件之后的所有action都不会向深层的View传递，统统都会传给父层View的onTouchEvent，就是说父层已经截获了这次touch事件，之后的action也不必询问onInterceptTouchEvent，在这次的touch事件之后发出的action时onInterceptTouchEvent不会再被调用，直到下一次touch事件的来临。如果onInterceptTouchEvent返回false，那么本次action将发送给更深层的View，并且之后的每一次action都会询问父层的onInterceptTouchEvent需不需要截获本次touch事件。只有ViewGroup才有onInterceptTouchEvent方法，因为一个普通的View肯定是位于最深层的View，只有ViewGroup才有onInterceptTouchEvent方法，因为一个普通的View肯定是位于最深层的View，touch能够传到这里已经是最后一站了，肯定会调用View的onTouchEvent()。

 底层View的getParent().requestDisallowInterceptTouchEvent(true)

 对于底层的View来说，有一种方法可以阻止父层的View获取touch事件，就是调用getParent().requestDisallowInterceptTouchEvent(true)方法。一旦底层View收到touch的action后调用这个方法那么父层View就不会再调用onInterceptTouchEvent了，也无法截获以后的action（如果父层ViewGroup和最底层View需要截获不同焦点，或不同手势的touch，不能使用这个写死）。

 曾经开发过程中遇到的两个示例：左边是处理ViewPager和ListView的冲突，纪录水平和垂直方向的偏移量，如果水平方向的偏移更多的话就让ViewPager处理pager滑动

 右边处理的ViewPager和ImageBanner的滑动冲突，同样是纪录偏移量，如果发生在ImageBanner上的水平偏移量大于垂直偏移量的话就让banner滚动

 想想为什么右边是重写dispatchTouchEvent方法而不是onInterceptTouchEvent方法？
 *
 * */
