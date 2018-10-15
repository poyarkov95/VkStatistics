package vkstatistic.apoyark.com.vkstatistics.ui.base.RxBus

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class RxBus {

    companion object {
        private val subjectMap: HashMap<Int, PublishSubject<Any>> = HashMap()
        private val disposableMap: HashMap<Any, CompositeDisposable> = HashMap()

        fun getSubject(subjectCode: Int): PublishSubject<Any>? {
            var subject: PublishSubject<Any>? = subjectMap[subjectCode]
            if (subject == null) {
                subject = PublishSubject.create()
                subject.subscribeOn(AndroidSchedulers.mainThread())
                subjectMap[subjectCode] = subject
            }
            return subject
        }


        fun getCompositeDisposable(uiElement: Any): CompositeDisposable {
            var compositeDisposable: CompositeDisposable? = disposableMap[uiElement]
            if (compositeDisposable == null) {
                compositeDisposable = CompositeDisposable()
                disposableMap[uiElement] = compositeDisposable
            }
            return compositeDisposable
        }

        fun subscribe(code: Int, uiElement: Any, consumer: Consumer<Any>) {
            val subscribe = getSubject(code)
                    ?.debounce(1000, TimeUnit.MILLISECONDS)
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe(consumer)
            getCompositeDisposable(uiElement).add(subscribe)
        }

        fun unregister(uiElement: Any) {
            disposableMap.remove(uiElement)?.dispose()
        }

        fun publish(subjectCode: Int, message: Any?) {
            getSubject(subjectCode)?.let { it.onNext(message) }
        }
    }
}