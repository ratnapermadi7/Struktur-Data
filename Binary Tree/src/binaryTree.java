

public class binaryTree {
    private Node root;

    public binaryTree(){
        root = null;
    }

    public void insert(int data){
        Node se = new Node(data);
        if (root==null){
            root = se;
        }
        //Cek dari child root
        else {
            Node current = root;
            Node parent;

           //angka genap masuk ke LeftChild
            if (data%2==0){
                parent = current;
                current = current.leftChild;
                if (current==null){
                    parent.leftChild = se;
                }
                else {
                    while(true){
                        parent = current;
                        if(data<current.data){
                            current=current.leftChild;
                            if (current==null){
                                parent.leftChild = se;
                                break;
                            }
                        }
                        else {
                            current=current.rightChild;
                            if (current==null){
                                parent.rightChild = se;
                                break;
                            }
                        }
                    }

                }
            }
            //angka genap masuk ke rightChild
            else {
                parent = current;
                current = current.rightChild;
                if (current==null){
                    parent.rightChild = se;
                }
                else {
                    //Menentukan child dari parent berdasarkan besar kecilnya data
                    while(true){
                        parent = current;
                        if(data<current.data){
                            current=current.leftChild;
                            if (current==null){
                                parent.leftChild = se;
                                break;
                            }
                        }
                        else {
                            current=current.rightChild;
                            if (current==null){
                                parent.rightChild = se;
                                break;
                            }
                        }
                    }

                }
            }

        }

    }

    public void delete(int data){
        Node child, parent, current, subChild;
        //hapus data yg ada di root
        if (root.data == data){
            //penggantian node yang akan dihapus
            //cek leftChild
            if (root.leftChild!=null){
                parent = root.leftChild;
                child = parent.leftChild;
                child.rightChild = parent.rightChild;
                parent.rightChild = root.rightChild;
                root = parent;

            }
            //cek rightChild
            else if (root.rightChild!=null){
                parent = root.rightChild;
                child = parent.rightChild;
                child.leftChild = parent.leftChild;
                root = parent;
            }
            else {
                root = null;
            }
        }
        //hapus data pada child dari root
        else {
            parent = find(data); // cari data yg ada di parent
            //Jika angka di child Genap
            if (parent.leftChild != null) {
                if (parent.leftChild.data == data){
                    current = parent.leftChild;
                    if (current.leftChild != null) {
                        child = current.leftChild;
                        if (child.rightChild != null) {
                            subChild = child.rightChild;

                            while (subChild.rightChild != null) {
                                subChild = subChild.rightChild;
                            }
                            if (current.rightChild != null) {
                                subChild.rightChild = current.rightChild;
                            }
                        } else if (current.rightChild != null) {
                            child.rightChild = current.rightChild;
                        }
                        parent.leftChild = child;
                        System.out.println(data+" dihapus dari Binary Tree");

                    } else if (current.rightChild != null) {
                        child = current.rightChild;
                        parent.leftChild = child;
                        System.out.println(data+" dihapus dari Binary Tree");
                    } else {
                        parent.leftChild = null;
                        System.out.println(data+" dihapus dari Binary Tree");
                    }
                } else {
                    System.out.println("Data yg akan dihapus tidak ada di binary Tree!!");
                }

            }
            //Jika angka di child Ganjil
            else if (parent.rightChild != null) {
                if (parent.rightChild.data == data){
                    current = parent.rightChild;

                    if (current.leftChild != null) {
                        child = current.leftChild;
                        if (child.rightChild != null) {
                            subChild = child.rightChild;
                            while (subChild.rightChild != null) {
                                subChild = subChild.rightChild;
                            }
                            if (current.rightChild != null) {
                                subChild.rightChild = current.rightChild;
                            }
                        } else if (current.rightChild != null) {
                            child.rightChild = current.rightChild;
                        }
                        parent.rightChild = child;
                        System.out.println(data+" dihapus dari Binary Tree");


                    } else if (current.rightChild != null) {
                        child = current.rightChild;
                        parent.rightChild = child;
                        System.out.println(data+" dihapus dari Binary Tree");
                    }
                    else {
                        parent.rightChild = null;
                        System.out.println(data+" dihapus dari Binary Tree");
                    }
                }
            }
            else {
                System.out.println("Nilai yang ingin dihapus tidak terdaftar dalam pohon biner...");
            }
        }
    }

    public void minimun(){
        //Inisialisasi
        Node current = root;
        Node current2 = current.rightChild;
        Node lastGenap = current;
        Node lastGanjil = current;

            /*
            Karena yang diminta minimun, otomatis pola yang dapat dilakukan adalah
            mencari child extreme paling kiri milik parent dari masing-masing child
            milik root (child Ganjil dan Genap
             */

        //Mengubah current ke posisi extreme paling kiri dari child parent genap
        while(current!=null){
            lastGenap = current;
            current = current.leftChild;
        }

        //Mengubah current ke posisi extreme paling kiri dari child parent ganjil
        while(current2!=null){
            lastGanjil = current2;
            current2 = current2.leftChild;
        }

        //Perbandingan antara 2 data child ganjil dan genap agar mengetahui minimum yang sesungguhnya
        if (lastGenap.data > lastGanjil.data){
            System.out.println("Data minimum : "+lastGanjil.data);
        }
        else if(lastGenap.data < lastGanjil.data) {
            System.out.println("Data minimum  : "+lastGenap.data);
        }
        else {
            System.out.println("Data minimum  : "+root.data);
        }

    }

    public Node find(int key){
        Node current = root;
        Node parent = current;

        if (root==null){
            System.out.println("Binary Tree kosong");
        }
        //Jika angka yang dicari berada di root
        else if (root.data==key){
            System.out.println(key+" ada di : root");
        }
        //Jika yang dicari berada di child dari root
        else {
            //Mencari di child Genap
            if (key%2==0){
                current = current.leftChild;

                if (current==null){
                    System.out.println(key+" tidak ada di Binary Tree");
                }
                else {

                    if (current.data==key){
                        System.out.println(key+" ada di : left child dari root "+root.data);
                    }
                    else {
                        //Mencari dalam child Genap lebih dalam dengan pengulangan
                        while(true){
                            parent = current;
                            //Jika key yang dicari < key saat ini yang telah dicek
                            if (key<current.data){
                                current = current.leftChild;
                                if(current!=null && key==current.data){
                                    System.out.println(key+" ada di : leftChild dari parent "+parent.data);
                                    break;
                                }
                                else if(current==null){
                                    System.out.println(key+" tidak ada di binaryTree");
                                    break;
                                }
                            }
                            //Jika key yang dicari > key saat ini yang telah dicek
                            else {
                                current = current.rightChild;
                                if(current!=null && key==current.data){
                                    System.out.println(key+" ada di : right child dari parent "+parent.data);
                                    break;
                                }
                                else if(current==null){
                                    System.out.println(key+" tidak ada di binaryTree");
                                    break;
                                }
                            }
                        }
                    }

                }
            }
            //Mencari di child Ganjil
            else {
                current = current.rightChild;

                if (current==null){
                    System.out.println(key+" tidak ada di binaryTree");
                }
                else {

                    if (current.data==key){
                        System.out.println(key+" ada di : right child dari root "+root.data);
                    }
                    else {
                        //Mencari dalam child Ganjil lebih dalam dengan pengulangan
                        while(true){
                            parent = current;
                            //Jika key yang dicari < key saat ini yang telah dicek
                            if (key<current.data){
                                current = current.leftChild;
                                if(current!=null && key==current.data){
                                    System.out.println(key+" ada di : left child dari parent "+parent.data);
                                    break;
                                }
                                else if(current==null){
                                    System.out.println(key+" tidak ada di binaryTree");
                                    break;
                                }
                            }
                            //Jika key yang dicari > key saat ini yang telah dicek
                            else {
                                current = current.rightChild;
                                if(current!=null && key==current.data){
                                    System.out.println(key+" ada di : right child dari parent "+parent.data);
                                    break;
                                }
                                else if(current==null){
                                    System.out.println(key+" tidak ada di binaryTree");
                                    break;
                                }
                            }
                        }

                    }

                }
            }

        }
        return parent;
    }
}
