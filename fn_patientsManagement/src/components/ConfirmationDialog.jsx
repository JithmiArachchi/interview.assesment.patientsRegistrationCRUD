import { Fragment  } from "react";
import { Dialog, Transition } from "@headlessui/react";

const ConfirmationDialog = ({ open, title="Confirm Action",message="Are You Sure?", onConfirm,onCancel }) => {  
    return (
    <Transition appear show={open} as={Fragment}>
      <Dialog as="div" className="relative z-50" onClose={onCancel}>
        <div className="flex items-end justify-center min-h-screen pt-4 px-4 pb-20 text-center sm:block sm:p-0"/>
            <Transition.Child   
            as={Fragment}
            enter="ease-out duration-300"
            enterFrom="opacity-0"
            enterTo="opacity-100"
            leave="ease-in duration-200"
            leaveFrom="opacity-100"
            leaveTo="opacity-0"
            >
                <div className="fixed inset-0 bg-black bg-opacity-25" />
            </Transition.Child>

            <div className="fixed inset-0 overflow-y-auto">
                <div className="flex min-h-full items-center justify-center p-4 text-center">
                    <Transition.Child
                    as={Fragment}
                    enter="ease-out duration-300"
                    enterFrom="opacity-0 scale-95"
                    enterTo="opacity-100 scale-100"
                    leave="ease-in duration-200"
                    leaveFrom="opacity-100 scale-100"
                    leaveTo="opacity-0 scale-95"
                    >   
                <Dialog.Panel className="w-full max-w-md transform overflow-hidden rounded-2xl
                 bg-white p-6 text-left align-middle shadow-xl transition-all">
                    <Dialog.Title
                    as="h3"
                    className="text-lg font-medium leading-6 text-gray-900" 
                    >
                        {title}
                    </Dialog.Title>
                    <div className="mt-2">
                        <p className="text-sm text-gray-500">
                            {message}
                        </p>
                    </div>
                    <div className="mt-4 flex justify-end space-x-2">
                        <button
                        type="button"
                        className="inline-flex justify-center rounded-md border border-transparent
                            bg-red-600 px-4 py-2 text-sm font-medium text-white hover:bg-red-700
                            focus:outline-none focus-visible:ring-2 focus-visible:ring-red-500
                            focus-visible:ring-offset-2"
                        onClick={onCancel}
                        >
                            Cancel
                        </button>
                        <button
                        type="button"
                        className="inline-flex justify-center rounded-md border border-transparent
                            bg-green-600 px-4 py-2 text-sm font-medium text-white hover:bg-green-700
                            focus:outline-none focus-visible:ring-2 focus-visible:ring-green-500
                            focus-visible:ring-offset-2"    
                        onClick={onConfirm}
                        >
                            Delete 
                        </button>
                    </div>
                </Dialog.Panel>
                </Transition.Child>
            </div>
        </div>
      </Dialog>
    </Transition>
    
    );
};
export default ConfirmationDialog;