package daniel.perez.minecraft.coronacraft.rx

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import net.minecraftforge.eventbus.api.Event
import net.minecraftforge.eventbus.api.IEventBus
import java.util.function.Consumer

inline fun <reified T: Event> IEventBus.events(): Observable<T>
{
    return object: Observable<T>()
    {
        override fun subscribeActual(observer: Observer<in T>)
        {
            val listener = object: Consumer<T>, Disposable
            {
                var disposed = false

                override fun accept(event: T)
                {
                    observer.onNext(event)
                }

                override fun isDisposed(): Boolean = disposed

                override fun dispose()
                {
                    this@events.unregister(this)
                }
            }
            this@events.addListener(listener)
            observer.onSubscribe(listener)
        }

    }
}